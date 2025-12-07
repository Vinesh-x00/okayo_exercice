package fr.okayo.exercice.vat;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VatRateRepository extends JpaRepository<VatRate, UUID> {

    /**
     * Find the applicable VAT rate for a given category and date.
     * @param categoryCode VAT category.
     * @param date date of VAT
     * @return Optional of VAT entity.
     */
    @Query("SELECT v FROM VatRate v WHERE v.vatCategory.code = :categoryCode " +
            "AND :date >= v.startDate " +
            "AND (:date <= v.endDate OR v.endDate IS NULL)")
    Optional<VatRate> findActiveRate(@Param("categoryCode") @NotNull String categoryCode,
                                     @Param("date") @NotNull LocalDate date);
}
