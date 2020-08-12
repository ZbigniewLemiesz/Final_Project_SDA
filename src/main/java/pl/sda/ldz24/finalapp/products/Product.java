package pl.sda.ldz24.finalapp.products;

import lombok.Getter;
import pl.sda.ldz24.finalapp.categories.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String productName;
    private String description;
    private String urlImage;
    private Integer stockAmount;
    private BigDecimal price;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public static Product createNewFromDTO(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.category = category;
        product.description = productDTO.getDescription();
        product.price = productDTO.getPrice();
        product.productName = productDTO.getProductName();
        product.productType = productDTO.getProductType();
        product.stockAmount = productDTO.getStockAmount();
        product.urlImage = productDTO.getImageUrl();
        product.id = productDTO.getId();
        return product;
    }

    public void apply(ProductDTO productDTO, Category category) {
        this.category = category;
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.productName = productDTO.getProductName();
        this.productType = productDTO.getProductType();
        this.stockAmount = productDTO.getStockAmount();
        this.urlImage = productDTO.getImageUrl();
    }
}
