package pl.sda.ldz24.finalapp.categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.ldz24.finalapp.store.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    private Long id;
    private String categoryName;
    private Long parentId;
    private Integer depth;

    public Category(Long id, String categoryName, Integer depth) {
        this.id = id;
        this.categoryName = categoryName;
        this.depth = depth;
    }

    public void applyParentId(Long parentId) {
        this.parentId = parentId;
    }
}
