package pl.sda.ldz24.finalapp.categories;

import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CategoryDAO {

    private AtomicInteger categoriesCounter = new AtomicInteger(10000000);
    @Getter
    private List<Category> categoriesList;
    private static CategoryDAO categoryDAO;

    private CategoryDAO() {
        categoriesList = populateCategories();
    }

    private List<Category> populateCategories() {
        List<String> lines = readLinesFromFile();
        List<Category> categories = lines.stream()
                .map(line -> parseLineToCategory(line))
                .collect(Collectors.toList());
        return populateParentId(categories);
    }

    private List<Category> populateParentId(List<Category> categories) {
        for (Category category : categories) {
            Integer currentDepth = category.getDepth();
            Long parentId = categories.stream()
                    .filter(c -> c.getDepth().equals(currentDepth - 1))
                    .filter(c -> c.getId() < category.getId())
                    .map(c -> c.getId())
                    .sorted(Comparator.reverseOrder())
                    .findFirst()
                    .orElse(null);
            category.applyParentId(parentId);
        }
        return categories;
    }

    private Category parseLineToCategory(String line) {
        int depth = countDepth(line);
        String categoryName = line.trim();
        Long categoryId = Long.valueOf(categoriesCounter.getAndIncrement());

        return new Category(categoryId, categoryName, depth);
    }

    private int countDepth(String line) {
        if (!line.startsWith(" ")) {
            return 0;
        }
        String[] split = line.split("\\S+");
        return split[0].length();
    }

    private List<String> readLinesFromFile() {
        try {
            URI fileUri = this.getClass().getClassLoader().getResource("kategorie.txt").toURI(); // file:c:\sdsgsg
            Path path = Paths.get(fileUri); // c:\
            return Files.lines(path).collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    static CategoryDAO getInstance() {
        if (categoryDAO == null) {
            synchronized (CategoryDAO.class) {
                if (categoryDAO == null) {
                    categoryDAO = new CategoryDAO();
                }
            }
        }
        return categoryDAO;
    }
}
