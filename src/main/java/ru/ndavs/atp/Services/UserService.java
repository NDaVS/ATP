package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DAO.UserDAO;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.Repositories.UserRepository;
import ru.ndavs.atp.models.Bus;
import ru.ndavs.atp.models.Driver;
import ru.ndavs.atp.models.Users;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    private final BusRepository busRepository;


    public Stream<Users> getUsers() {
        return userDAO.getUsers();
    }

    public UserResponseDTO loginResponse(AccessDTO accessDTO) throws IllegalStateException {
        try {
            Users user = userDAO.getUserByLogin(accessDTO);
            if (user.getPassword().equals(accessDTO.password)) {
                UserResponseDTO userResponseDTO = userDAO.loginResponse(accessDTO);
                return userResponseDTO;
            }
            throw new IllegalStateException("Wrong login/password");
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }

    }

    public UserDTO getUserById(Long id) {
        try {
            Users user = userRepository.getReferenceById(id);
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
    }

    public UserDTO addNewUser(PostUserDTO postUserDTO){
        try {
            Users user = modelMapper.map(postUserDTO, Users.class);
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
    }

    public UserDTO updateUserById(PostUserDTO postUserDTO, Long id){
        try {
            Users user = userRepository.getReferenceById(id);
            user.setFirst_name(postUserDTO.getFirst_name());
            user.setFather_name(postUserDTO.getFather_name());
            user.setLast_name(postUserDTO.getLast_name());
            user.setPhone_number(postUserDTO.getPhone_number());
            user.setRole(postUserDTO.getRole());
            user.setLogin(postUserDTO.getLogin());
            user.setPassword(postUserDTO.getPassword());
            user.setEmail(postUserDTO.getEmail());
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
    }

    public UserDTO updateUserCred(String login, String password, Long id){
        try {
            Users user = userRepository.getReferenceById(id);
            user.setLogin(login);
            user.setPassword(password);
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }
    public UserDTO deleteUserById(Long id){
        try{
            Users user =  userRepository.getReferenceById(id);
            userRepository.delete(user);
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
    }
//DRIVERS
    public DriverDTO registerDriver(PostDriverDTO postDriverDTO) {
        try {
            if (driverRepository.findDriverByLogin(postDriverDTO.login).isPresent()) {
                throw new IllegalStateException("This login is already taken");
            }
            Driver driver = modelMapper.map(postDriverDTO, Driver.class);
            driverRepository.save(driver);
            return modelMapper.map(driver, DriverDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }

    public List<DriverDTO> getDrivers() {
        try {
            List<Driver> drivers = driverRepository.findAll();
            return drivers.stream().map(driver -> modelMapper.map(driver, DriverDTO.class)).toList();
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }

    public DriverDTO updateDriverBus(PostDriverDTO postDriverDTO,Long driverId) {
        try {
            Driver driver = driverRepository.findById(driverId).get();
            driver.setDriver_id(postDriverDTO.getDriver_id());
            driver.setEmail(postDriverDTO.getEmail());
            driver.setPhone_number(postDriverDTO.getPhone_number());
            driver.setFirst_name(postDriverDTO.getFirst_name());
            driver.setLast_name(postDriverDTO.getLast_name());
            driver.setFather_name(postDriverDTO.getFather_name());
            driverRepository.save(driver);
//            responseDTO.data.bus = modelMapper.map(bus, BusDTO.class);
            return modelMapper.map(driver, DriverDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }
    public DriverDTO updateDriverCred(String login, String password, Long id){
        try {
            Driver driver = driverRepository.findById(id).get();
            driver.setLogin(login);
            driver.setPassword(password);
            driverRepository.save(driver);
//            responseDTO.data.bus = modelMapper.map(bus, BusDTO.class);
            return modelMapper.map(driver, DriverDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }

    public DriverDTO deleteDriverById(Long driverId) {
        try {
            Driver driver = driverRepository.findById(driverId).get();
            driverRepository.delete(driver);
            return modelMapper.map(driver, DriverDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }

    public DriverDTO getDriverById(Long driverId) {
        try {
            Driver driver = driverRepository.findById(driverId).get();
            return modelMapper.map(driver, DriverDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить пользователя: " + e.getMessage());
        }
    }

    public ResponseDTO setBusAndDriver(Long bus_id, Long driver_id){
        try{

            Driver driver = driverRepository.findById(driver_id).get();
            Bus bus = busRepository.findById(bus_id).get();
            driver.setBus_id(bus_id);
            bus.setDriver(driver);
            driverRepository.save(driver);
            busRepository.save(bus);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(200L);
            responseDTO.setMessage("Success");
            return responseDTO;
        }catch (Exception e) {
            throw new IllegalStateException("Не удалось установить связь водителя и автобуса: " + e.getMessage());
        }
    }
}
