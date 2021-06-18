package tacos.web.api;

import org.springframework.hateoas.CollectionModel;



public class TacoCollectionModel extends CollectionModel<TacoModel> {
  public TacoCollectionModel(CollectionModel<TacoModel> tacoResources) {
    TacoCollectionModel.of(tacoResources);
  }
}