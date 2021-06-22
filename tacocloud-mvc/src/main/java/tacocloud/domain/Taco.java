package tacocloud.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
//    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    //    @Size(min = 1, message = "You must choose at least 1 ingredient")
//    @JoinTable(name = "Taco_Ingredient",
//            joinColumns = @JoinColumn(name = "taco_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @ManyToMany(targetEntity = Ingredient.class)
    @NotNull(message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
