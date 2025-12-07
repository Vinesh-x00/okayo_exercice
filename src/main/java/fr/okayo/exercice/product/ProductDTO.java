package fr.okayo.exercice.product;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductDTO(String designation, BigDecimal priceExclTax, String vtaCategory) {

    public ProductDTO {
        Objects.requireNonNull(designation);
        Objects.requireNonNull(vtaCategory);
    }
}
