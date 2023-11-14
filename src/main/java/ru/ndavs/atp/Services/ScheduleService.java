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
import ru.ndavs.atp.models.TripStations;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final StationRepository stationRepository;
    private final ScheduleRepository scheduleRepository;
    private final BusRepository busRepository;
    private final TripStationRepository tripStationRepository;

    public ScheduleService(StationRepository stationRepository, ScheduleRepository scheduleRepository, BusRepository busRepository, TripStationRepository tripStationRepository) {
        this.stationRepository = stationRepository;
        this.scheduleRepository = scheduleRepository;
        this.busRepository = busRepository;
        this.tripStationRepository = tripStationRepository;
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
            schedule.setId(postScheduleDTO.getSchedule_id());
            TripStations tripStations = new TripStations();

            // перебрать все станции из postScheduleDTO и добавить в tripStation из базы данных
            List<Station> stations = new ArrayList<Station>();
            for (StationDTO station : postScheduleDTO.getStations()) {
                stations.add(stationRepository.findById(station.getId()).get());
            }
            tripStations.setStations(stations);
            tripStationRepository.save(tripStations);
            schedule.setStations(tripStations);

            scheduleRepository.save(schedule);
            return schedule;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить рейс");
        }
    }
}
