package fr.okayo.exercice.invoice;

import fr.okayo.exercice.exceptions.BadRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record InvoiceDTO(
        String customer,
        String reference,
        LocalDate issueDate,
        LocalDate dueDate,
        InvoiceStatus status,
        List<InvoiceLineDTO> lines
) {
    public InvoiceDTO {
        Objects.requireNonNull(customer);
        Objects.requireNonNull(reference);
        Objects.requireNonNull(issueDate);
        Objects.requireNonNull(lines);

        if(lines.isEmpty()) {
            throw new BadRequest("Details de facture est vide");
        }
    }
}
