package pl.sda.ldz24.finalapp.users;

import lombok.Getter;
import pl.sda.ldz24.finalapp.store.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Getter
    private String roleName ;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }
}
