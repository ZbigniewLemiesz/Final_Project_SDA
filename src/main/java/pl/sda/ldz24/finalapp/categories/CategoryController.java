package pl.sda.ldz24.finalapp.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String displayCategories(@RequestParam(required = false) String searchText, Model model) {
        List<CategoryDTO> categoriesByText = categoryService.findCategoriesByText(searchText);
        model.addAttribute("catsdata", categoriesByText);

        return "categories";
    }
}
