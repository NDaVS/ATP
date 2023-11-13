package ru.ndavs.atp.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostScheduleDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.ScheduleRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.Repositories.TripStationRepository;
import ru.ndavs.atp.models.Schedule;
import ru.ndavs.atp.models.Station;
import ru.ndavs.atp.models.TripStation;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final StationRepository stationRepository;
    private final TripStationRepository tripStationRepository;
    private final ScheduleRepository scheduleRepository;
    private final BusRepository busRepository;

    public ScheduleService(StationRepository stationRepository, TripStationRepository tripStationRepository, ScheduleRepository scheduleRepository, BusRepository busRepository) {
        this.stationRepository = stationRepository;
        this.tripStationRepository = tripStationRepository;
        this.scheduleRepository = scheduleRepository;
        this.busRepository = busRepository;
    }

    public List<Schedule> getSchedule() {
        return scheduleRepository.findAll();
    }

    //    Добавить вывод
    @Transactional
    public Schedule addTrip(PostScheduleDTO postScheduleDTO) {
        try {
            Schedule schedule = new Schedule(
                    postScheduleDTO.getTime_to(),
                    postScheduleDTO.getTime_from()
            );
            schedule.setBus(busRepository.findById(postScheduleDTO.getBus_id()).get());
            schedule.setId(postScheduleDTO.getId());
            TripStation tripStation = new TripStation();
            tripStation.setTrip_id(postScheduleDTO.getId());
            // перебрать все станции из postScheduleDTO и добавить в tripStation из базы данных
            List<Station> stations = new ArrayList<Station>();
            for (StationDTO station : postScheduleDTO.getStations()) {
                stations.add(stationRepository.findById(station.getId()).get());
            }
            tripStation.setStations(stations);
            //        tripStation.setSchedule(schedule);
            tripStationRepository.save(tripStation);
            schedule.setStations(tripStation);
            scheduleRepository.save(schedule);
            return schedule;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить рейс");
        }
    }
}
