package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Ingredient;

class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {
  public IngredientModelAssembler() {
    super(IngredientHateoasController.class, IngredientModel.class);
  }

  @Override
  protected IngredientModel instantiateModel(Ingredient entity) {
    return new IngredientModel(entity);
  }

  // this function add self link to IngredientModel
  @Override
  public IngredientModel toModel(Ingredient entity) {
    return createModelWithId(entity.getId(), entity);
  }
}