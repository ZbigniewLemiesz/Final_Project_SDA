package pl.sda.ldz24.finalapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.ldz24.finalapp.users.Countries;
import pl.sda.ldz24.finalapp.users.UserRegistrationDTO;
import pl.sda.ldz24.finalapp.users.UserService;

@Controller
@RequestMapping("/auth") // kontroller do obsługi logowania i rejestracji
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register") //wyświetlanie formularza
    public String registerForm(Model model) {
        model.addAttribute("countries", Countries.values()); //dodanie możliwych krajów do wyboru w formularzu
        model.addAttribute("registrationData", new UserRegistrationDTO()); //wrzucenie nowego pustego obiektu do uzupełnienia w trakcie rejestracji
        return "registrationPage"; //nazwa pliku html z /resources/templates
    }

    @PostMapping("/register") //przesłanie danych do formularza
    public String createNewUser(UserRegistrationDTO userRegistrationDTO) {
        userService.addUser(userRegistrationDTO);
        return "redirect:/auth/login";
    }

    @GetMapping("/login") //wyswietlenie formularza logowania
    public String loginUser() {
        return "loginPage";
    }
}
