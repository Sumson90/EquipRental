package pl.equipRental.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class UserAssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long assetId;
    private String assetName;
    private String assetSerialNumber;
}
