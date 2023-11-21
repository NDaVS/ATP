package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostTripDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.DTO.TripStationDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.Repositories.TripStationRepository;
import ru.ndavs.atp.models.Station;
import ru.ndavs.atp.models.TripStations;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    private final TripStationRepository tripRepository;
    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;


//    public TripService(TripStationRepository tripRepository, StationRepository stationRepository, ModelMapper modelMapper, BusRepository busRepository, DriverRepository driverRepository) {
//        this.tripRepository = tripRepository;
//        this.stationRepository = stationRepository;
//        this.modelMapper = modelMapper;
//        this.busRepository = busRepository;
//        this.driverRepository = driverRepository;
//    }

    public ResponseDTO getTrip() {
        try {
            List<TripStations>  trips = tripRepository.findAll();
            List<TripStationDTO> tripStationDTOList = new ArrayList<>();
            for (TripStations trip : trips) {
                TripStationDTO tripStationDTO = modelMapper.map(trip, TripStationDTO.class);
//            tripStationDTO.setDriverDTO(modelMapper.map(trip.getDriver(), DriverDTO.class));
//            tripStationDTO.driverDTO.setBusDTO(modelMapper.map(trip.getDriver().getBus(), BusDTO.class));
//            tripStationDTO.setStation(modelMapper.map(schedule.getStations(), TripStationDTO.class));
                tripStationDTOList.add(tripStationDTO);
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            responseDTO.data = tripStationDTOList;
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить рейсы: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }


    public ResponseDTO addTrip(PostTripDTO postTripDTO) {
        try {
            TripStations tripStations = new TripStations(
                    postTripDTO.getTime_to(),
                    postTripDTO.getTime_from()
            );
//            tripStations.setDriver(driverRepository.findById(postTripDTO.getDriver_id()).get());
//            tripStations.setTrip_id(postTripDTO.getTrip_id());
            List<Station> stations = new ArrayList<>();
            for (StationDTO station : postTripDTO.getStations()) {
                stations.add(stationRepository.findById(station.getId()).get());
            }
            tripStations.setStations(stations);

            tripRepository.save(tripStations);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            responseDTO.data = modelMapper.map(tripStations, TripStationDTO.class);
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить рейс: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }
    public ResponseDTO deleteTrip(Long id) {
        try {
            TripStations tripStations = tripRepository.findById(id).get();
            tripStations.setStations(null);
            tripRepository.delete(tripStations);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось удалить рейс: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }

    public ResponseDTO updateTrip(Long id, PostTripDTO postTripDTO) {
        try {
            TripStations tripStations = tripRepository.findById(id).get();
            tripStations.setTime_to(postTripDTO.getTime_to());
            tripStations.setTime_from(postTripDTO.getTime_from());
//            tripStations.setDriver(driverRepository.findById(postTripDTO.getDriver_id()).get());
            List<Station> stations = new ArrayList<>();
            for (StationDTO station : postTripDTO.getStations()) {
                stations.add(stationRepository.findById(station.getId()).get());
            }
            tripStations.setStations(stations);
            tripRepository.save(tripStations);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            responseDTO.data = modelMapper.map(tripStations, TripStationDTO.class);
            return responseDTO;

        } catch (Exception e) {
            throw new IllegalStateException("Не удалось обновить рейс: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }
}
