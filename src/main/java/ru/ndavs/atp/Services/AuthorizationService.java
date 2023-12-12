package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.DepartureDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.MobileDTO.MobileDepartureDTO;
import ru.ndavs.atp.MobileDTO.MobileLoginResponseDTO;
import ru.ndavs.atp.MobileDTO.MobileTripDTO;
import ru.ndavs.atp.Repositories.*;
import ru.ndavs.atp.models.Departures;
import ru.ndavs.atp.models.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final DriverRepository driverRepository;
    private final DepartureRepository departureRepository;
    private final DepartureService departureService;


    public MobileLoginResponseDTO mobileAuthorization(AccessDTO accessDTO){
        Optional<Driver> driver = driverRepository.findDriverByLogin(accessDTO.login);
        if (driver.isEmpty()) throw new IllegalStateException("The user does not exist");
        if (!driver.get().getPassword().equals(accessDTO.password)) throw new IllegalStateException("Invalid password");

        Driver _driver = driver.get();
        MobileLoginResponseDTO response = new MobileLoginResponseDTO();
        response.setFirst_name(_driver.getFirst_name());
        response.setLast_name(_driver.getLast_name());
        response.setPatronymic(_driver.getFather_name());
        response.setToken("token");
        response.setBus_code(_driver.getBus_id());



        List<Departures> deps = departureRepository.findDeparturesByDate(accessDTO.getDate());
        List<DepartureDTO> dtos = new ArrayList<>();
        for (Departures d: deps){
            if (d.getTrip().getDriver().getId().equals(_driver.getId())){
                dtos.add(departureService.DTO_maker(d));
            }
        }
        List<MobileDepartureDTO> departureDTOS = new ArrayList<>();

        for (DepartureDTO d: dtos){
            MobileDepartureDTO mobileDepartureDTO = new MobileDepartureDTO();
            mobileDepartureDTO.setDate(d.getDate());
            mobileDepartureDTO.setId(d.getId());
            mobileDepartureDTO.setTickets(d.getTickets());
            mobileDepartureDTO.setStatus(d.getStatus());
            MobileTripDTO mobileTripDTO = new MobileTripDTO();

            mobileTripDTO.setBus(d.getTrip().getBus());
            mobileTripDTO.setDays(d.getTrip().getDays());
            mobileTripDTO.setDriver(d.getTrip().getDriver());
            mobileTripDTO.setId(d.getTrip().getId());
            mobileTripDTO.setDeparture_time(d.getTrip().getDeparture_time());
            List<String> stations = new ArrayList<>();
            for(StationDTO stationDTO: d.getTrip().getRoad().getStations()){
                stations.add(stationDTO.getName());
            }
            mobileTripDTO.setStations(stations);

            mobileDepartureDTO.setTrip(mobileTripDTO);
            departureDTOS.add(mobileDepartureDTO);
        }

        response.setDaily_schedule(departureDTOS);

        return response;

    }


}
