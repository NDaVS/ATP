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
        return args -> {
            Station station_1 = new Station(
                    "School"
            );
            Station station_2 = new Station(
                    "FEFU"
            );
            Station station_3 = new Station(
                   "Death🤡"
            );
            repository.saveAll(List.of(station_1, station_2, station_3));
        };
    }
}
