package pl.sda.ldz24.finalapp.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @GetMapping("/geturl")
    public String testPage(@RequestParam(required = false) String user, Model model) { //model przychodzi od Springa
        model.addAttribute("inputValue", user);
        model.addAttribute("username", user == null ? "Anonim" : user); //tę wartość TL podmieni w htmlu
        return "testPage"; //przy pomocy Thymeleafa uzupełniony zostanie szablon strony
        //Thymeleaf jest template enginem -> silnikiem szablonów (np html) -> zamienia wskazane miejsca na wartości z modelu
    }

    @PostMapping("/posturl")
    public String post(@RequestParam(required = false) String user, Model model) {
        model.addAttribute("inputValue", user);
        model.addAttribute("username", user == null ? "Anonim" : user);
        return "testPage";
    }
}
