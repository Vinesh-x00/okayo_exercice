package fr.okayo.exercice.invoice;

import fr.okayo.exercice.exceptions.BadRequest;

import java.util.Objects;
import java.util.UUID;

public record InvoiceLineDTO(UUID productId, int quantity) {

    public InvoiceLineDTO {
        Objects.requireNonNull(productId);
        if(quantity <= 0) {
            throw new BadRequest("quantité de produit est nulle ou negative");
        }
    }
}
