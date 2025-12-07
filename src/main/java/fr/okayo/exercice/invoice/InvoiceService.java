package fr.okayo.exercice.invoice;

import fr.okayo.exercice.customer.CustomerRepository;
import fr.okayo.exercice.exceptions.BadRequest;
import fr.okayo.exercice.exceptions.ResourceNotFoundException;
import fr.okayo.exercice.product.ProductRepository;
import fr.okayo.exercice.vat.VatRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final VatRateRepository vatRateRepository;
    private final ProductRepository productRepository;


    /**
     * find invoice by reference
     * @param reference invoice reference
     * @return invoice
     */
    public Invoice findByReference(String reference) {
        Objects.requireNonNull(reference, "reference is null");
        var res = invoiceRepository.findByReference(reference);
        return res.orElseThrow(() -> new ResourceNotFoundException("facture non trouvé"));
    }

    /**
     * find invoices by customer code.
     * @param customerCode customer code.
     * @return list of invoices with given customer code.
     */
    public List<Invoice> findByCustomerCode(String customerCode) {
        Objects.requireNonNull(customerCode,"CustomerCode is null");
        return invoiceRepository.findByCustomer_CustomerCode(customerCode);
    }

    /**
     * find invoices by status.
     * @param status status of invoice
     * @return list of invoices with given status.
     */
    public List<Invoice> findByStatus(InvoiceStatus status) {
        Objects.requireNonNull(status,"status is null");
        return invoiceRepository.findByStatus(status);
    }

    /**
     * find invoices by status customer code.
     * @param CustomerCode customer code.
     * @param status invoice status.
     * @return list of invoices with given status and customer code.
     */
    public List<Invoice> findByCustomerAndStatus(String CustomerCode, InvoiceStatus status) {
        Objects.requireNonNull(CustomerCode);
        Objects.requireNonNull(status);
        return invoiceRepository.findByCustomer_CustomerCodeAndStatus(CustomerCode, status);
    }

    /**
     * get all invoices
     * @return list of invoices.
     */
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    /**
     * Create new invoice.
     * @param dto invoice DTO
     * @return created invoice.
     */
    public Invoice createInvoice(InvoiceDTO dto) {
        Objects.requireNonNull(dto);
        if(dto.dueDate().isBefore(LocalDate.now())) {
            dto = new InvoiceDTO(dto.customer(), dto.reference(), dto.issueDate(), dto.dueDate(), InvoiceStatus.PAYEE, dto.lines());
        }

        var invoice = new Invoice();

        var customer = customerRepository.findByCustomerCode(dto.customer())
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));

        invoice.setCustomer(customer);
        invoice.setIssueDate(dto.issueDate());
        invoice.setDueDate(dto.dueDate());
        invoice.setStatus(dto.status() == null ? InvoiceStatus.BROUILLON : dto.status());
        invoice.setReference(dto.reference());

        var totalExclTax = BigDecimal.ZERO; // Total HT
        var totalInclTax = BigDecimal.ZERO; // Total TTC

        var invoiceLines = new ArrayList<InvoiceLine>();

        for(var lineDto : dto.lines()) {
            var product = productRepository
                    .findById(lineDto.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));

            var rate = vatRateRepository
                    .findActiveRate(product.getVatCategory().getCode(), LocalDate.now())
                    .orElseThrow(() -> new BadRequest("Aucun taux de TVA actif trouvé pour la catégorie"));

            var line = new InvoiceLine();
            line.setInvoice(invoice);
            line.setProduct(product);
            line.setProductName(product.getDesignation());
            line.setUnitPrice(product.getPriceExclTax());
            line.setAppliedVatRate(rate);
            line.setQuantity(lineDto.quantity());

            // Montant HT Ligne = Prix Unitaire * Quantité
            var lineTotalHt = product.getPriceExclTax().multiply(BigDecimal.valueOf(lineDto.quantity()));

            // Montant TVA Ligne = Montant HT * Taux
            var lineVatAmount = lineTotalHt.multiply(rate.getRate());

            var lineTotalTtc = lineTotalHt.add(lineVatAmount);

            totalExclTax = totalExclTax.add(lineTotalHt);
            totalInclTax = totalInclTax.add(lineTotalTtc);

            invoiceLines.add(line);
        }

        invoice.setLines(List.copyOf(invoiceLines));
        invoice.setTotalExclTax(totalExclTax);
        invoice.setTotalInclTax(totalInclTax);

        return invoiceRepository.save(invoice);
    }

}
