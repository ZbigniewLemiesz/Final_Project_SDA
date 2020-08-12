package pl.sda.ldz24.finalapp.products;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.ldz24.finalapp.categories.Category;
import pl.sda.ldz24.finalapp.categories.CategoryService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("categories", categoryService.findCategoriesByText(""));
        return "addProducts";
    }

    @GetMapping
    public String displayProducts(Model model) {
        List<ProductDTO> foundProducts = productService.findProducts();
        model.addAttribute("productsList", foundProducts);
        return "products";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<ProductDTO> productById = productService.findProductById(id);
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("categories", categoryService.findCategoriesByText(""));
        if (productById.isPresent()) {
            model.addAttribute("productDTO", productById.get());
            return "editProduct";
        }
        return "redirect:/products/add";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Integer stockAmount,
                             @RequestParam String productName,
                             @RequestParam String description,
                             @RequestParam String imageUrl,
                             @RequestParam BigDecimal price,
                             @RequestParam ProductType productType,
                             @RequestParam Long categoryId) {

        ProductDTO productDTO = new ProductDTO(stockAmount,
                productName,
                description,
                imageUrl,
                price,
                productType,
                categoryId);

        productService.addProduct(productDTO);
        return "redirect:/products/add";
    }

    @PostMapping
    public String editProduct(ProductDTO productDTO) {
        productService.editProduct(productDTO);
        return "redirect:/products";
    }
}
