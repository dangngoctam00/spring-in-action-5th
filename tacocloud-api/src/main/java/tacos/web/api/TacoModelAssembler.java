package tacos.web.api;


import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Taco;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

  public TacoModelAssembler() {
    super(DesignTacoHateoasController.class, TacoModel.class);
  }

  @Override
  protected TacoModel instantiateModel(Taco entity) {
    return new TacoModel(entity);
  }

  @Override
  public TacoModel toModel(Taco taco) {
    return createModelWithId(taco.getId(), taco);
  }

}
