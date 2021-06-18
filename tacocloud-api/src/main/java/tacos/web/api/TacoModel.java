package tacos.web.api;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;
import tacos.Taco;

@Relation(value="taco", collectionRelation="tacos")
public class TacoModel extends RepresentationModel<TacoModel> {

  private static final IngredientModelAssembler
            ingredientAssembler = new IngredientModelAssembler();
  
  @Getter
  private final String name;

  @Getter
  private final Date createdAt;

  @Getter
  private final Collection<IngredientModel> ingredients;
  
  public TacoModel(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients =
            ingredientAssembler.toCollectionModel(taco.getIngredients()).getContent();
  }
  
}
