//tag::recents[]
package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//end::recents[]
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//tag::recents[]
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/design",                      // <1>
        produces = "application/json")
@CrossOrigin(origins = "*")        // <2>
public class DesignTacoController {
    private TacoRepository tacoRepo;

    final EntityLinks entityLinks;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepo, EntityLinks entityLinks) {
        this.tacoRepo = tacoRepo;
        this.entityLinks = entityLinks;
    }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {                 //<3>
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());
    return tacoRepo.findAll(page).getContent();
  }

//  @GetMapping("/recenth")
//  public Resources<TacoResource> recentTacosH() {
//    PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending());
//    List<Taco> tacos = tacoRepo.findAll(page).getContent();
//
//    List<TacoResource> tacoResources =
//        new TacoResourceAssembler().toResources(tacos);
//    Resources<TacoResource> recentResources =
//        new Resources<TacoResource>(tacoResources);
//    recentResources.add(
//        linkTo(methodOn(DesignTacoController.class).recentTacos())
//        .withRel("recents"));
//    return recentResources;
//  }


//ControllerLinkBuilder.linkTo(DesignTacoController.class)
//.slash("recent")
//.withRel("recents"));


//    @GetMapping("/recenth")
//    public CollectionModel<TacoResource> recenthTacos() {
//        PageRequest page = PageRequest.of(
//                0, 12, Sort.by("createdAt").descending());
//        List<Taco> tacos = tacoRepo.findAll(page).getContent();
//
//        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
//
//        EntityModel<TacoResource> tacosResources = new EntityModel<>(tacoResources);
//
////        Link recentsLink = ControllerLinkBuilder
////            .linkTo(DesignTacoController.class)
////            .slash("recent")
////            .withRel("recents");
//
//        Link recentsLink =
//                linkTo(methodOn(DesignTacoController.class).recenthTacos())
//                        .withRel("recents");
//        tacosResources.add(recentsLink);
//        return tacosResources;
//    }

    //tag::postTaco[]
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
    //end::postTaco[]


//  @GetMapping("/{id}")
//  public Taco tacoById(@PathVariable("id") Long id) {
//    Optional<Taco> optTaco = tacoRepo.findById(id);
//    if (optTaco.isPresent()) {
//      return optTaco.get();
//    }
//    return null;
//  }

//  @GetMapping("/{id}")
//  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
//    Optional<Taco> optTaco = tacoRepo.findById(id);
//    if (optTaco.isPresent()) {
//      return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
//    }
//    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//  }

    //--------------------
    // return resource object for one taco

    @GetMapping("/{id}")
    public ResponseEntity<TacoResource> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            TacoResource taco = new TacoResource(optTaco.get());
//            EntityModel<TacoResource> taco_res = new EntityModel<>(taco);
            Link tacoLink = linkTo(methodOn(DesignTacoController.class).tacoById(id)).withRel("taco_link");
            taco.add(tacoLink);
            return new ResponseEntity<TacoResource>(taco, HttpStatus.OK);
        }
        return new ResponseEntity<TacoResource>(HttpStatus.NOT_FOUND);
    }


//tag::recents[]
}
//end::recents[]

