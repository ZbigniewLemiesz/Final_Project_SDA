package pl.sda.ldz24.finalapp.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private Integer stockAmount;
    private String productName;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private ProductType productType;
    private Long categoryId;
    private String categoryName;

    public ProductDTO() {
    }

    public ProductDTO(Integer stockAmount, String productName, String description, String imageUrl, BigDecimal price, ProductType productType, Long categoryId) {
        this.stockAmount = stockAmount;
        this.productName = productName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.productType = productType;
        this.categoryId = categoryId;
    }

    public static ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.id = product.getId();
        productDTO.categoryId = product.getCategory().getId();
        productDTO.categoryName = product.getCategory().getCategoryName();
        productDTO.description = product.getDescription();
        productDTO.imageUrl = product.getUrlImage();
        productDTO.price = product.getPrice();
        productDTO.productName = product.getProductName();
        productDTO.productType = product.getProductType();
        productDTO.stockAmount = product.getStockAmount();

        return productDTO;


    }
}
