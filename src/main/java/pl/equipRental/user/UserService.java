package pl.equipRental.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.equipRental.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }



}
