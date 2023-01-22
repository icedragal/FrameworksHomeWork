package me.egorzhuravlev.frameworkshomework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.egorzhuravlev.frameworkshomework.model.Recipe;
import me.egorzhuravlev.frameworkshomework.services.RecipeService;
import me.egorzhuravlev.frameworkshomework.services.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции для рецептов")
public class RecipeController {
    private final RecipeService recipeService;
    private final ValidateService validateService;
    public RecipeController(RecipeService recipeService, ValidateService validateService) {
        this.recipeService = recipeService;
        this.validateService = validateService;
    }
    @Operation(summary = "Добавление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Добавление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некоректные параметры")
    })
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Recipe recipe){
        if(validateService.isNotValid(recipe)){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @Operation(summary = "Получение рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некоректные параметры")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> get(@PathVariable long id) {
        return ResponseEntity.of(recipeService.get(id));
    }

    @Operation(summary = "Изменение рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Изменение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некоректные параметры")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable long id,
                                 @RequestBody Recipe recipe){
        if(validateService.isNotValid(recipe)){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.of(recipeService.update(id, recipe));
    }

    @Operation(summary = "Удаление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Удаление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некоректные параметры")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable long id){
        return ResponseEntity.of(recipeService.delete(id));
    }

    @Operation(summary = "Получение всех рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "")
    })
    @GetMapping()
    public Map<Long, Recipe> getAll() {
        return recipeService.getAll();
    }
}
