package fr.okayo.exercice.coustomer;

import java.util.Objects;

public record CustomerDTO(String name, String customerCode, String address, String zipCode, String city) {

    public CustomerDTO {
        Objects.requireNonNull(name);
        Objects.requireNonNull(customerCode);
        Objects.requireNonNull(address);
        Objects.requireNonNull(zipCode);
        Objects.requireNonNull(city);
    }

}
