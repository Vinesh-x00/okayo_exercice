package fr.okayo.exercice.product;

import fr.okayo.exercice.vat.VatCategory;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Produit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "vatCategory")
public class Product {

    @Id
    @Column(name = "id_produit", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "designation", nullable = false, length = 100)
    private String designation;

    @Column(name = "prix_ht", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceExclTax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_categorie_tva", nullable = false)
    private VatCategory vatCategory;
}
