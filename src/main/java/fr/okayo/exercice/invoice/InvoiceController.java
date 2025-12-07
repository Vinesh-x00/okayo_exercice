package fr.okayo.exercice.invoice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
@Tag(name = "Invoice", description = "endpoints for creating and retrieving invoices")
public class InvoiceController {
    private final @NotNull InvoiceService invoiceService;

    @Operation(
            summary = "retrieve invoice by reference"
    )
    @GetMapping("/{reference}")
    public Invoice getInvoiceByReference(
            @Parameter(description = "invoice reference")
            @NotNull @PathVariable String reference) {
        return invoiceService.findByReference(reference);
    }

    @Operation(
            summary = "search invoices by customer code or invoice status",
            description = "search invoices by customer code or invoice status if customer code or invoice status not provided, it's return all invoices"
    )
    @GetMapping("/search")
    public List<Invoice> searchInvoice(
            @Parameter(description = "customer code")
            @RequestParam(name = "customer_code", required = false) String customerCode,

            @Parameter(description = "invoice status")
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

    @Operation(
            summary = "Create new Invoice"
    )
    @PostMapping
    public Invoice createInvoice(
            @Parameter(description = "invoice data")
            @NotNull @RequestBody InvoiceDTO request) {
        return invoiceService.createInvoice(request);
    }

}
