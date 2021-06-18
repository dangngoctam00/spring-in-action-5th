package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.Taco;
import tacos.data.TacoRepository;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/tacos_hateoas", produces = "application/hal+json")
@CrossOrigin("*")
public class DesignTacoHateoasController {
    private TacoRepository tacoRepo;
    final EntityLinks entityLinks;

    @Autowired
    public DesignTacoHateoasController(TacoRepository tacoRepo, EntityLinks entityLinks) {
        this.tacoRepo = tacoRepo;
        this.entityLinks = entityLinks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoModel> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            TacoModel taco = new TacoModel(optTaco.get());
            Link tacoLink = linkTo(methodOn(DesignTacoHateoasController.class).tacoById(id)).withRel("taco");
            taco.add(tacoLink);
            return new ResponseEntity<>(taco, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/recenth")
    public CollectionModel<TacoModel> recenthTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        CollectionModel<TacoModel> tacosResources = new TacoModelAssembler().toCollectionModel(tacos);

        Link recentsLink =
                linkTo(methodOn(DesignTacoHateoasController.class).recenthTacos())
                        .withRel("recents");
        tacosResources.add(recentsLink);
        return tacosResources;
    }
}
