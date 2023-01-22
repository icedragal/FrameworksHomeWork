package me.egorzhuravlev.frameworkshomework.services;

import me.egorzhuravlev.frameworkshomework.model.Ingredient;
import me.egorzhuravlev.frameworkshomework.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private long idGenerator = 1;
    public String add(Recipe recipe){
        recipes.put(idGenerator++, recipe);
        return  "Добавлен рецепт: \"" + recipe.getTitle() + "\", его ID: " + idGenerator;
    }

    public Optional<Recipe> get(long id){
        return Optional.ofNullable(recipes.get(id));
    }

    public Optional<Recipe> update(long id, Recipe recipe) {
        return Optional.ofNullable(recipes.replace(id, recipe));
    }

    public Optional<Recipe> delete(long id) {
        return Optional.ofNullable(recipes.remove(id));
    }

    public Map<Long, Recipe> getAll() {
        return new HashMap<>(recipes);
    }
}
