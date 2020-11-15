package guru.springframework.domain;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    @Lob
    private Byte[] image;

    @OneToOne  (cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Category> categories = new HashSet<>();

    /**
     * Bidirectional setter
     * @param notes
     */
    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//        categories.stream().forEach(categorie -> categorie.getRecipes().add(this));
//    }

    /**
     * Bidirectional setter
     * @param ingredients
     */
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        ingredients.stream().forEach(ingredient -> ingredient.setRecipe(this));
    }
}
