package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.data.IngredientRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/ingredients_hateoas", produces="application/hal+json")
@CrossOrigin(origins="*")
public class IngredientHateoasController {
    private IngredientRepository ingredientRepo;
    final EntityLinks entityLinks;

    @Autowired
    public IngredientHateoasController(IngredientRepository ingredientRepo, EntityLinks entityLinks) {
        this.ingredientRepo = ingredientRepo;
        this.entityLinks = entityLinks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientModel> getIngredientById(@PathVariable String id) {
        Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
        if (ingredient != null) {
            return new ResponseEntity<>(new IngredientModelAssembler().toModel(ingredient), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<IngredientModel>> getIngredients() {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Link ingredientsLink = linkTo(methodOn(IngredientHateoasController.class).getIngredients()).withRel("ingredients_link");
        return new ResponseEntity<>(new IngredientModelAssembler().toCollectionModel(ingredients).add(ingredientsLink) , HttpStatus.OK);
    }
}