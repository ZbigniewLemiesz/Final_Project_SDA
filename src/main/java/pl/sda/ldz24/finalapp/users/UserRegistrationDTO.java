package pl.sda.ldz24.finalapp.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private boolean preferEmails;

}
