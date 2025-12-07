package fr.okayo.exercice.invoice;

import fr.okayo.exercice.coustomer.Customer;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Facture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"lines", "customer"})
public class Invoice {

    @Id
    @Column(name = "id_facture")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reference_facture", nullable = false, unique = true, length = 100)
    private String reference;

    @Column(name = "date_emission", nullable = false)
    private LocalDate issueDate;

    @Column(name = "date_echeance")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 10)
    private InvoiceStatus status = InvoiceStatus.BROUILLON;

    @Column(name = "total_ht", precision = 10, scale = 2)
    private BigDecimal totalExclTax;

    @Column(name = "total_ttc_globale", precision = 10, scale = 2)
    private BigDecimal totalInclTax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceLine> lines;
}
