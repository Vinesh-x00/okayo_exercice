package fr.okayo.exercice.vat;



import fr.okayo.exercice.exceptions.BadRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public record VatDTO(
        String category,
        BigDecimal rate,
        LocalDate startDate,
        Optional<LocalDate> endDate) {

    public VatDTO {
        Objects.requireNonNull(category);
        Objects.requireNonNull(rate);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);

        if(rate.signum() < 0) {
            throw new BadRequest("taux tva est nagative");
        }

        endDate.ifPresent(d -> {
            if(!d.isAfter(startDate)) {
                throw new BadRequest("date debut est apres date fin");
            }
        });
    }
}
