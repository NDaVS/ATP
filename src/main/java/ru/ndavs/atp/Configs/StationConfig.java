package ru.ndavs.atp.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.models.Station;

import java.util.List;

@Configuration
public class StationConfig {
    @Bean
    CommandLineRunner commandLineRunnerStation(StationRepository repository) {
        List<Station> stations = repository.findAll();
        if (!stations.isEmpty()) {
            return null;
        }
        return args -> {


            Station station_1 = new Station(
                    "School"
            );
            Station station_2 = new Station(
                    "FEFU"
            );
            Station station_3 = new Station(
                    "Вертодром"
            );
            Station station_4 = new Station(
                    "ТЭЦ"
            );
            Station station_5 = new Station(
                    "Парк"
            );
            Station station_6 = new Station(
                    "Площадь"
            );
            Station station_7 = new Station(
                    "Порт"
            );
            repository.saveAll(List.of(station_1, station_2, station_3, station_4, station_5, station_6, station_7));

        };
    }
}
