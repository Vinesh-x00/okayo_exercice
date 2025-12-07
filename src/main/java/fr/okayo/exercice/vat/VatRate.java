package fr.okayo.exercice.vat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Taux_Tva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "vatCategory")
public class VatRate {

    @Id
    @Column(name = "id_tva", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    private UUID id;

    @Column(name = "taux", nullable = false, precision = 5, scale = 4)
    @PositiveOrZero
    private BigDecimal rate;

    @Column(name = "date_debut", nullable = false)
    @NotNull
    private LocalDate startDate;

    @Column(name = "date_fin")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_categorie", nullable = false)
    @NotNull
    private VatCategory vatCategory;
}
