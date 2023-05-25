package pl.equipRental.assignment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.equipRental.assets.Asset;
import pl.equipRental.user.User;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;


}
