package fr.okayo.exercice.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.okayo.exercice.product.Product;
import fr.okayo.exercice.vat.VatRate;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Detail_Facture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"invoice", "product", "appliedVatRate"}) // CRITIQUE : Exclure le parent (invoice)
public class InvoiceLine {

    @Id
    @Column(name = "id_detail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "designation_produit", nullable = false, length = 100)
    private String productName;

    @Column(name = "prix_unitaire", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "quantite", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facture", nullable = false)
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tva", nullable = false)
    private VatRate appliedVatRate;
}