package pl.equipRental.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
}
