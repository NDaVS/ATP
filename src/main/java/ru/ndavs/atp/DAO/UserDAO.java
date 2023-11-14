package ru.ndavs.atp.DAO;

import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


    public UserDAO(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public Users getUserByLogin(AccessDTO accessDTO) throws IllegalStateException {
        Optional<Users> user = userRepository.findUserByLogin(accessDTO.login);
        if (user.isEmpty()) {
            throw new IllegalStateException("Wrong login");
        }
        return user.get();
    }


    public Stream<Users> getUsers() {
        Stream<Users> users = userRepository.findAll().stream();
        return users;
    }

    public LoginResponseDTO loginResponse(AccessDTO accessDTO) {
        LoginResponseDTO loginResponseDTO = userRepository.findUserByLogin(accessDTO.login).map(user -> modelMapper.map(user, LoginResponseDTO.class)).orElseThrow(() -> new IllegalStateException("Wrong login"));
        loginResponseDTO.setToken("token");
        return loginResponseDTO;
    }
}
