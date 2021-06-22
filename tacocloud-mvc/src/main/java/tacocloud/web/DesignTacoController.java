package tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import tacocloud.data.IngredientRepository;
import tacocloud.data.TacoRepository;
import tacocloud.data.UserRepository;
import tacocloud.domain.Ingredient;
import tacocloud.domain.Ingredient.Type;
import tacocloud.domain.Order;
import tacocloud.domain.Taco;
import tacocloud.domain.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository designRepo;
    private UserRepository userRepo;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo, UserRepository userRepo){
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList()));
        }
    }

    @ModelAttribute("taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model ,Principal principal){
        String userName = principal.getName();
        User user = userRepo.findByUsername(userName);
        model.addAttribute("user", user);
        return "design";
    }

    @PostMapping
    public String  processDesign(@Valid @ModelAttribute("taco") Taco taco, Errors errors, @ModelAttribute("order") Order order) {
        if (errors.hasErrors()){
            return "design";
        }
        Taco saved = designRepo.save(taco);
        // save method of order object
        order.addDesign(taco);
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    } 
}
