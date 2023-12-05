package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostTripDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.DTO.RoadDTO;
import ru.ndavs.atp.DTO.TripDTO;
import ru.ndavs.atp.Repositories.*;
import ru.ndavs.atp.models.Days;
import ru.ndavs.atp.models.Driver;
import ru.ndavs.atp.models.Trip;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final RoadRepository roadRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final DaysRepository daysRepository;
    private final ModelMapper modelMapper;

    public List<Trip> getAllTrips() {
        try {
            List<Trip> tripList = tripRepository.findAll();
//            List<TripDTO> dtoList = new ArrayList<>();
//            for (Trip trip : tripList) {
//                TripDTO tripDTO = modelMapper.map(trip, TripDTO.class);
//                RoadDTO roadDTO = setTime(trip);
//                tripDTO.setRoad(roadDTO);
//                dtoList.add(tripDTO);
//            }
            return tripList;
        }catch (Exception e) {
            throw new IllegalStateException("Не удалось получить рейсы: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public Trip getTripById(Long id) {
        try {
            Trip trip = tripRepository.findById(id).get();
//            TripDTO tripDTO = modelMapper.map(trip, TripDTO.class);
//            RoadDTO roadDTO = setTime(trip);
//            tripDTO.setRoad(roadDTO);
            return trip;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить рейс: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public Trip addNewTrip(PostTripDTO postTripDTO) {
        try {
            Trip trip = modelMapper.map(postTripDTO, Trip.class);
            //SET DRIVER
            trip.setBus(busRepository.findById(postTripDTO.getBus_id()).get());
            Driver driver = driverRepository.findById(postTripDTO.getDriver_id()).get();
            trip.setDriver(driver);
            trip.setRoad(roadRepository.findById(postTripDTO.getRoad_id()).get());
            List<Days> days = new ArrayList<>();
            for (Long id: postTripDTO.getDays_id()){
                days.add(daysRepository.findById(id).get());
            }
            tripRepository.save(trip);
            return trip;
        }catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить рейс: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public Trip updateTripById(PostTripDTO postTripDTO, Long id) {
        try {
            Trip trip = tripRepository.findById(id).get();
            trip.setBus(busRepository.findById(postTripDTO.getBus_id()).get());
            trip.setDriver(driverRepository.getReferenceById(postTripDTO.getDriver_id()));
            trip.setRoad(roadRepository.findById(postTripDTO.getRoad_id()).get());
            List<Days> days = new ArrayList<>();
            for (Long day_id: postTripDTO.getDays_id()){
                days.add(daysRepository.getReferenceById(day_id));
            }
            tripRepository.save(trip);
            return trip;
        }catch (Exception e) {
            throw new IllegalStateException("Не удалось обновить рейс: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    private RoadDTO setTime(Trip trip){
        RoadDTO roadDTO = modelMapper.map(trip.getRoad(), RoadDTO.class);
        roadDTO.setTime(List.of(trip.getRoad().getTime().split(" ")));
        roadDTO.setPrice(List.of(trip.getRoad().getPrice().split(" ")));
        return  roadDTO;
    }

    private TripDTO getTripDTO(Trip trip) {
        try {
            tripRepository.save(trip);
            RoadDTO roadDTO = setTime(trip);
            TripDTO tripDTO = modelMapper.map(trip, TripDTO.class);
            tripDTO.setRoad(roadDTO);
            return tripDTO;
        } catch (Exception e) {
            throw new IllegalStateException("something went wrong ... : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public Object deleteTripById(Long id) {
        try {
            Trip trip = tripRepository.findById(id).get();
            trip.setRoad(null);
            //driver
            tripRepository.delete(trip);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось удалить рейс: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
}
