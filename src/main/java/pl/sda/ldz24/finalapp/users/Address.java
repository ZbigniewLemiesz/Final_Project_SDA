package pl.sda.ldz24.finalapp.users;

import lombok.Getter;
import pl.sda.ldz24.finalapp.store.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
public class Address extends BaseEntity {

    private String country;
    private String city;
    private String street;
    private String zipCode;

    public static Address valueOf(String country, String city, String street, String zipCode) {
        Address address = new Address();
        address.country = country;
        address.city = city;
        address.street = street;
        address.zipCode = zipCode;

        return address;
    }

}
