package fr.okayo.exercice.invoice;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final @NotNull InvoiceService invoiceService;

    @GetMapping("/{reference}")
    public Invoice getInvoiceByReference(@PathVariable String reference) {
        return invoiceService.findByReference(reference);
    }

    @GetMapping("/search")
    public List<Invoice> searchInvoice(
            @RequestParam(name = "customer_code", required = false) String customerCode,
            @RequestParam(name = "status", required = false) InvoiceStatus status
    ) {
        if((customerCode == null || customerCode.isEmpty()) && status == null) {
            return invoiceService.getAll();
        } else if (customerCode == null) {
            return invoiceService.findByStatus(status);
        } else if (status == null){
            return invoiceService.findByCustomerCode(customerCode);
        } else {
            return invoiceService.findByCustomerAndStatus(customerCode, status);
        }
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody InvoiceDTO request) {
        return invoiceService.createInvoice(request);
    }

}
