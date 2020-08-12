package pl.sda.ldz24.finalapp.store;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.ldz24.finalapp.users.*;

import static pl.sda.ldz24.finalapp.users.Role.ROLE_ADMIN;
import static pl.sda.ldz24.finalapp.users.Role.ROLE_USER;

@Service
public class DataSeed implements InitializingBean {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception { // klasa wywoływana po uruchomieniu springa
        long count = roleRepository.count();
        if (count == 0) {
            roleRepository.save(new Role(ROLE_ADMIN));
            roleRepository.save(new Role(ROLE_USER));
        }

        if (userRepository.count() == 0) {
            UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
            userRegistrationDTO.setCity("Łódź");
            userRegistrationDTO.setCountry(Countries.POLAND.name());
            userRegistrationDTO.setFirstName("Adam");
            userRegistrationDTO.setLastName("Nowak");
            userRegistrationDTO.setLogin("adam@nowak.pl");
            userRegistrationDTO.setPassword("123123");
            userRegistrationDTO.setStreet("Mickiewicza");
            userRegistrationDTO.setZipCode("12-345");
            userRegistrationDTO.setPreferEmails(true);

            userService.addUser(userRegistrationDTO);

        }
    }

}
