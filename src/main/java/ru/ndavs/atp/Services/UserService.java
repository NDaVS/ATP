package ru.ndavs.atp.Services;

import org.springframework.stereotype.Service;
import ru.ndavs.atp.DAO.UserDAO;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.LoginResponseDTO;
import ru.ndavs.atp.models.Users;

import java.util.stream.Stream;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public Stream<Users> getUsers(){
        return userDAO.getUsers();
    }

    public LoginResponseDTO loginResponse(AccessDTO accessDTO) throws IllegalStateException {
        try {
            Users user = userDAO.getUserByLogin(accessDTO);
            if (user.getPassword().equals(accessDTO.password)) {
                return userDAO.loginResponse(accessDTO);
            }
            throw new IllegalStateException("Wrong login/password");
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }

    }

}
