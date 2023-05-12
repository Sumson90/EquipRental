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
                .map(UserMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    UserDto save(UserDto user) {
        Optional<User> userByPesel = userRepository.findByPesel((user.getPesel()));
        userByPesel.ifPresent(u ->
        {
            throw new DuplicatePeselException("Użytkownik z takim peselem już istnieje.");
        });
        return getUserDto(user);

    }


    Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(UserMapper.INSTANCE::toDto);
    }

    UserDto update(UserDto user) {
        Optional<User> userByPesel = userRepository.findByPesel(user.getPesel());
        userByPesel.ifPresent(u -> {
            if (!u.getId().equals(user.getId()))
                throw new DuplicatePeselException("Użytkownik z takim peselem już istnieje.");
        });
        return getUserDto(user);
    }

    private UserDto getUserDto(UserDto user) {
        User userEntity = UserMapper.INSTANCE.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.INSTANCE.toDto(savedUser);
    }
}
