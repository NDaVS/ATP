package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.MobileDTO.MobileLoginResponseDTO;
import ru.ndavs.atp.MobileDTO.RouteDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.BusSpecRepository;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.Repositories.TripRepository;
import ru.ndavs.atp.models.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;
    private final TripRepository tripRepository;
    private final BusRepository busRepository;
    private final BusSpecRepository busSpecRepository;


    public MobileLoginResponseDTO mobileAuthorization(AccessDTO accessDTO){
        Optional<Driver> driver = driverRepository.findDriverByLogin(accessDTO.login);
        if (driver.isEmpty()) throw new IllegalStateException("The user does not exist");
        if (!driver.get().getPassword().equals(accessDTO.password)) throw new IllegalStateException("Invalid password");

        Driver _driver = driver.get();
        MobileLoginResponseDTO response = new MobileLoginResponseDTO();
        response.setFirst_name(_driver.getFirst_name());
        response.setLast_name(_driver.getLast_name());
        response.setPatronymic(_driver.getSurname());
        response.setToken("token");
        response.setBus_code(_driver.getBus_id());



        List<Trip> trips = tripRepository.findByDriver(_driver);
        List<RouteDTO> tripsDTO = trips.stream()
                .map(trip -> {
                    RouteDTO routeDTO = new RouteDTO();
                    routeDTO.setId(trip.getId());
                    routeDTO.setTime_start(trip.getDeparture_time());

                    List<GroupStation> groupStationList = trip.getRoad().getGroup().getStations();
                    routeDTO.setTime_finish(String.valueOf(groupStationList.get(groupStationList.size() - 1).getTime()));

                    List<String> stations = groupStationList.stream()
                            .map(groupStation -> groupStation.getStation().getName())
                            .collect(Collectors.toList());
                    routeDTO.setStations(stations);

                    Bus bus = busRepository.findById(_driver.getBus_id()).get();
                    BusSpecs busSpecs = busSpecRepository.findByModel(bus.getModel());
                    routeDTO.setPlace_number(busSpecs.getNumber_of_sits());

                    return routeDTO;
                })
                .toList();

        response.setDaily_schedule(tripsDTO);
        return response;

    }


}
