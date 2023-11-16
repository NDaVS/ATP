package ru.ndavs.atp.Services;

import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostTripDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.Repositories.TripStationRepository;
import ru.ndavs.atp.models.Station;
import ru.ndavs.atp.models.TripStations;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    private final TripStationRepository tripRepository;
    private final StationRepository stationRepository;

    private final BusRepository busRepository;


    public TripService(TripStationRepository tripRepository, StationRepository stationRepository, BusRepository busRepository) {
        this.tripRepository = tripRepository;
        this.stationRepository = stationRepository;
        this.busRepository = busRepository;
    }

    public List<TripStations> getTrip() {
        try {
            return tripRepository.findAll();
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить рейсы: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }
//        return tripRepository.findAll();}

    public TripStations addTrip(PostTripDTO postTripDTO) {
        try {
            TripStations tripStations = new TripStations(
                    postTripDTO.getTime_to(),
                    postTripDTO.getTime_from()
            );
            tripStations.setBus(busRepository.findById(postTripDTO.getBus_id()).get());
            tripStations.setTrip_id(postTripDTO.getTrip_id());
            List<Station> stations = new ArrayList<Station>();
            for (StationDTO station : postTripDTO.getStations()) {
                stations.add(stationRepository.findById(station.getId()).get());
            }
            tripStations.setStations(stations);
            tripRepository.save(tripStations);
            return tripStations;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить рейс: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }
}
