package ru.ndavs.atp.DAO;

import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.LoginResponseDTO;
import ru.ndavs.atp.Repositories.UserRepository;
import ru.ndavs.atp.models.Users;

import java.util.Optional;
import java.util.stream.Stream;

@Service
//@RequiredArgsConstructor
public class UserDAO {
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Users getUserByLogin(AccessDTO accessDTO) throws IllegalAccessException {
        Optional<Users> user = userRepository.findUserByLogin(accessDTO.login);
        if (user.isEmpty()) {
            throw new IllegalAccessException("Wrong login");
        }
        return user.get();
    }


    public Stream<Users> getUsers() {
        Stream<Users> users = userRepository.findAll().stream();
        return users;
    }
    public LoginResponseDTO loginResponse(AccessDTO accessDTO){
        return new LoginResponseDTO( "aboba",  "director");
    }
}
