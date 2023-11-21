package ru.ndavs.atp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DAO.UserDAO;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.models.Bus;
import ru.ndavs.atp.models.Driver;
import ru.ndavs.atp.models.Users;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;

    private final BusRepository busRepository;


    public UserService(UserDAO userDAO, ModelMapper modelMapper, DriverRepository driverRepository, BusRepository busRepository) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
        this.driverRepository = driverRepository;
        this.busRepository = busRepository;
    }


    public Stream<Users> getUsers(){
        return userDAO.getUsers();
    }

    public ResponseDTO loginResponse(AccessDTO accessDTO) throws IllegalStateException {
        try {
            Users user = userDAO.getUserByLogin(accessDTO);
            if (user.getPassword().equals(accessDTO.password)) {
                UserResponseDTO userResponseDTO = userDAO.loginResponse(accessDTO);
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.message = "Success";
                responseDTO.data = userResponseDTO;
                return responseDTO;
            }
            throw new IllegalStateException("Wrong login/password");
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }

    }
    public ResponseDTO registerDriver(RegisterDriverDTO registerDriverDTO){
        try {
            if (driverRepository.findDriverByLogin(registerDriverDTO.login).isPresent()){
                throw new IllegalStateException("This login is already taken");
            }
            Driver driver = modelMapper.map(registerDriverDTO, Driver.class);
            driverRepository.save(driver);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "Success";
            responseDTO.data = modelMapper.map(driver, DriverDTO.class);
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }

    public ResponseDTO getDrivers() {
        try {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "Success";
            responseDTO.data = driverRepository.findAll();
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }

    public ResponseDTO updateDriverBus(Long driverId, Long busId) {
try {
            Driver driver = driverRepository.findById(driverId).get();
            Bus bus = busRepository.findById(busId).get();
            driver.setBus(bus);
            driverRepository.save(driver);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "Success";
            responseDTO.data = driver;
//            responseDTO.data.bus = modelMapper.map(bus, BusDTO.class);
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Something went wrong: ");
        }
    }
}
