package pl.sda.ldz24.finalapp.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> findCategoriesByText(String searchText) {
        List<Category> categoriesList = CategoryDAO.getInstance().getCategoriesList();
        List<CategoryDTO> categoryDTOS = categoriesList.stream()
                .map(category -> buildDTO(category))
                .collect(Collectors.toList());
        for (CategoryDTO categoryDTO : categoryDTOS) {
            markAsSelectedAndOpen(searchText, categoryDTO, categoryDTOS);
        }
        return categoryDTOS;
    }

    private CategoryDTO buildDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setId(category.getId());
        categoryDTO.setState(new CategoryState());
        return categoryDTO;
    }

    private void markAsSelectedAndOpen(String searchText, CategoryDTO categoryDTO, List<CategoryDTO> categories) {
        if (categoryDTO.getCategoryName().equals(searchText)) {
            categoryDTO.getState().setSelected(true);
            openParent(categoryDTO, categories);

        }
    }

    private void openParent(CategoryDTO categoryDTO, List<CategoryDTO> categories) {
        if (categoryDTO.getParentId() == null) {
            return;
        }
        CategoryDTO parent = categories.stream()
                .filter(c -> c.getId().equals(categoryDTO.getParentId()))
                .findFirst()
                .orElseGet(() -> superHiperSlowAndResourceConsumingMethod());
        System.out.println("Czy rodzic znaleziony? " + parent != null);
        parent.getState().setOpened(true);
        openParent(parent, categories);
    }

    private CategoryDTO superHiperSlowAndResourceConsumingMethod() {
        try {
            Thread.sleep(10000);
            System.out.println("yyyyyyyyyyyyy");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostConstruct //to uruchomi sie raz po stworzeniu kontekstu psringa (po utworzeniu tego beana)
    public void prepareCategories() {
        if (categoryRepository.count() == 0) {
            System.out.println("Nie ma żadnych kategorii!!! Tworzę nowe!!");
            CategoryDAO.getInstance().getCategoriesList().forEach(c -> {
                System.out.println("Tworzę kategorię :" + c.getCategoryName());
                categoryRepository.save(c);
            });
        }
    }
}
