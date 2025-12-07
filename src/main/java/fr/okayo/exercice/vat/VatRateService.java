package fr.okayo.exercice.vat;

import fr.okayo.exercice.exceptions.BadRequest;
import fr.okayo.exercice.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Service class represent VAT.
 */
@Service
public class VatRateService {
    private final VatRateRepository vatRateRepository;
    private final VatCategoryRepository vatCategoryRepository;

    public VatRateService(VatRateRepository rateRepo, VatCategoryRepository cateRepo) {
        vatRateRepository = Objects.requireNonNull(rateRepo, "VatRateRepository is null");
        vatCategoryRepository = Objects.requireNonNull(cateRepo, "vatCategoryRepository is null");
    }

    /**
     * Find active VAT by category.
     * @param categoryCode category code.
     * @return active VAT.
     */
    @Transactional
    public VatRate findActiveVAT(String categoryCode) {
        Objects.requireNonNull(categoryCode, "categoryCode is null");
        return vatRateRepository.findActiveRate(categoryCode, LocalDate.now())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie TVA introuvable"));
    }

    /**
     * find VAT by uuid.
     * @param id uuid of vat.
     * @return VAT associate with that uuid.
     */
    @Transactional
    public VatRate findById(UUID id) {
        Objects.requireNonNull(id, "uuid is null");
        return vatRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TVA introuvable ID: " + id.toString()));
    }

    /**
     * create new VAT, it will end the previous active VAT with give category.
     * @param dto VAT DTO.
     * @return created VAT entity.
     */
    @Transactional
    public VatRate createVAT(VatDTO dto) {
        VatRate rate = new VatRate();

        rate.setRate(dto.rate());
        rate.setStartDate(dto.startDate());
        dto.endDate().ifPresent(rate::setEndDate);

        var activeVAT = vatRateRepository
                .findActiveRate(dto.category(), LocalDate.now())
                .orElseThrow(() -> new BadRequest("Catégorie tva non trouvé"));

        activeVAT.setEndDate(LocalDate.now());
        vatRateRepository.save(activeVAT);
        rate.setVatCategory(activeVAT.getVatCategory());

        return vatRateRepository.save(rate);
    }

    /**
     * List all VAT Categories
     * @return list of Category.
     */
    @Transactional
    public List<VatCategory> getAllVatCategories() {
        return vatCategoryRepository.findAll();
    }
}
