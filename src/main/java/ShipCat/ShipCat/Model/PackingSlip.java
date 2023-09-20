package ShipCat.ShipCat.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackingSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Packing slip requires a reference number")
    private String referenceNumber;

    @ManyToMany
    @JoinTable(
            name = "packing_slip_product",
            joinColumns = @JoinColumn(name = "packing_slip_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

}
