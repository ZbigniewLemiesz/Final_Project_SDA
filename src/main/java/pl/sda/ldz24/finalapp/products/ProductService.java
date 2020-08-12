package pl.sda.ldz24.finalapp.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.ldz24.finalapp.categories.Category;
import pl.sda.ldz24.finalapp.categories.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii"));


        Product product = Product.createNewFromDTO(productDTO, category);
        productRepository.save(product);

    }

    public List<ProductDTO> findProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::fromProduct)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> findProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::fromProduct);
    }

    public void editProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii"));

        product.apply(productDTO, category);
        productRepository.save(product);
    }
}
