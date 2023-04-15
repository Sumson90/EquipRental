package pl.equipRental.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.equipRental.exception.DuplicatePeselException;
import pl.equipRental.user.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    UserDto save(UserDto user) {
        Optional<User> userBypPesel = userRepository.findByPesel((user.getPesel()));
        userBypPesel.ifPresent(u ->
        {
            throw new DuplicatePeselException("Użytkownik z takim peselem już istnieje.");
        });
        User userEntity = UserMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);

    }


}
