package pl.equipRental.assets;

import jakarta.persistence.*;
import lombok.*;
import pl.equipRental.assignment.Assignment;
import pl.equipRental.category.Category;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(unique = true)
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "asset")
    private List<Assignment> assignments = new ArrayList<>();


}
