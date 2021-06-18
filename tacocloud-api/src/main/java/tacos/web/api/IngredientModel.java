package tacos.web.api;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import tacos.Ingredient;
import tacos.Ingredient.Type;

public class IngredientModel extends RepresentationModel<IngredientModel> {

  @Getter
  private String name;

  @Getter
  private Type type;
  
  public IngredientModel(Ingredient ingredient) {
    this.name = ingredient.getName();
    this.type = ingredient.getType();
  }

}
