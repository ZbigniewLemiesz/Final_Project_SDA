package pl.sda.ldz24.finalapp.categories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private Long parentId;
    private CategoryState state;

    public String getText() {
        return id + "." + categoryName;
    }

    public String getParent() {
        return (parentId == null ? "#" : parentId.toString());
    }
}
