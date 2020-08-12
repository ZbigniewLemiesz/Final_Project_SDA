package pl.sda.ldz24.finalapp.users;


import pl.sda.ldz24.finalapp.store.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class User extends BaseEntity {

    private String login;
    private String password;
    @OneToOne
    private Address address;
    @ManyToMany
    private List<Role> roles;

    private String string;

    public static User valueOf(String login, String password, Address address, List<Role> roles) {

        User user = new User();
        user.login = login;
        user.password = password;
        user.address = address;
        user.roles = roles;
        return user;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public Address getAddress() {
        return this.address;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public String getString() {
        return this.string;
    }
}
