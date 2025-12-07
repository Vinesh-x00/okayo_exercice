package fr.okayo.exercice.vat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Categorie_TVA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VatCategory {
    @Id
    @Column(name = "code_categorie", length = 50)
    private String code;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotNull
    private String description;
}
