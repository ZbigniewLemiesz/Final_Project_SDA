package pl.sda.ldz24.finalapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Optional<WeatherAddressDTO> findWeatherAddressByEmail(String email) {
        return userRepository.findByLogin(email)
                .map(e -> e.getAddress()) //wykona się tylko jeśli optional nie będzie pusty
                .map(address -> WeatherAddressDTO.fromAddress(address)); //wykona się tylko jeśli optional nie będzie pusty
    }

    public void addUser(UserRegistrationDTO userRegistrationDTO) {
        boolean userExists = userRepository.findByLogin(userRegistrationDTO.getLogin())
                .isPresent();

        if (userExists) {
            throw new RuntimeException("Użytkownik istnieje");
        }

        userRegistrationDTOtoUser(userRegistrationDTO);
    }

    private User userRegistrationDTOtoUser(UserRegistrationDTO userDTO) {
        Role userRole = roleRepository.findByRoleName(Role.ROLE_USER)
                .orElseThrow(() -> new RuntimeException());

        Address address = populateAddress(userDTO);

        User user = populateUser(userDTO, userRole, address);

        addressRepository.save(address);
        userRepository.save(user);

        return user;
    }

    private User populateUser(UserRegistrationDTO userDTO, Role userRole, Address address) {
        User user = User.valueOf(
                userDTO.getLogin(),
                passwordEncoder.encode(userDTO.getPassword()),
                address,
                singletonList(userRole));
        return user;
    }

    private Address populateAddress(UserRegistrationDTO userDTO) {
        Address address = Address.valueOf(
                userDTO.getCountry(),
                userDTO.getCity(),
                userDTO.getStreet(),
                userDTO.getZipCode());
        return address;
    }
}
