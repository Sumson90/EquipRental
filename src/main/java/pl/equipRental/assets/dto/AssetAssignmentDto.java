package pl.equipRental.assets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssetAssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
}
