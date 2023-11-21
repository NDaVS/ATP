package ru.ndavs.atp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DAO.UserDAO;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.DriverDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.models.Driver;

import java.util.Optional;

@Service
public class AuthorizationService {
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;

    public AuthorizationService(UserDAO userDAO, ModelMapper modelMapper, DriverRepository driverRepository) {
        this.modelMapper = modelMapper;
        this.driverRepository = driverRepository;
    }

    public ResponseDTO mobileAuthorization(AccessDTO accessDTO){
        try{
            Optional<Driver> driver = driverRepository.findDriverByLogin(accessDTO.login);
            if (driver.get().getPassword().equals(accessDTO.password)){
                DriverDTO driverDTO = modelMapper.map(driver, DriverDTO.class);
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.code = 200L;
                responseDTO.message = "success";
                responseDTO.data = driverDTO;
                return responseDTO;

            }


        } catch (Exception e) {
            throw new IllegalStateException("something went wrong...");
        }
        return null;
    }
}
