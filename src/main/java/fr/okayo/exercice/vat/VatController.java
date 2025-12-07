package fr.okayo.exercice.vat;

import fr.okayo.exercice.invoice.InvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "VAT Rate", description = "Endpoints for creating and retrieving VAT rates")
@RestController
@RequestMapping("/api/vats")
@RequiredArgsConstructor
public class VatController {

    private final VatRateService vatRateService;

    @Operation(
            summary = "retrieve VAT rate by uuid"
    )
    @GetMapping("/{id}")
    public VatRate findByid(
            @Parameter(description = "uuid of vat")
            @NotNull @PathVariable UUID id) {
        return vatRateService.findById(id);
    }

    @Operation(
            summary = "get vat rate by category"
    )
    @GetMapping("/categories/{categoryCode}")
    public VatRate findActiveVAT(
            @Parameter(description = "category Code of VAT")
            @NotNull @PathVariable String categoryCode) {
        return vatRateService.findActiveVAT(categoryCode);
    }

    @Operation(
            summary = "get all VAT categories"
    )
    @GetMapping("/categories")
    public List<VatCategory> getAllVatCategories() {
        return vatRateService.getAllVatCategories();
    }

    @Operation(
            summary = "create new VAT rate"
    )
    @PostMapping
    public VatRate createVat(
            @Parameter(description = "new VAT data")
            @NotNull @RequestBody VatDTO request) {
        return vatRateService.createVAT(request);
    }
}
