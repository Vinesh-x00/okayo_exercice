package fr.okayo.exercice.vat;

import fr.okayo.exercice.invoice.InvoiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vats")
@RequiredArgsConstructor
public class VatController {

    private final VatRateService vatRateService;

    @GetMapping("/{id}")
    public VatRate findByid(@PathVariable UUID id) {
        return vatRateService.findById(id);
    }

    @GetMapping("/categories/{categoryCode}")
    public VatRate findActiveVAT(@PathVariable String categoryCode) {
        return vatRateService.findActiveVAT(categoryCode);
    }

    @GetMapping("/categories")
    public List<VatCategory> getAllVatCategories() {
        return vatRateService.getAllVatCategories();
    }

    @PostMapping
    public VatRate createVat(@RequestBody VatDTO request) {
        return vatRateService.createVAT(request);
    }
}
