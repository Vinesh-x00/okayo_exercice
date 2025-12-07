package fr.okayo.exercice.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @Column(name = "code_client", length = 100, unique = true)
    @NotNull
    private String customerCode;

    @Column(name = "nom", nullable = false, length = 100)
    @NotNull
    private String name;

    @Column(name = "adresse", length = 500)
    @NotNull
    private String address;

    @Column(name = "code_postal", length = 50)
    @NotNull
    private String zipCode;

    @Column(name = "ville", length = 100)
    @NotNull
    private String city;
}
