package ru.ndavs.atp.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ndavs.atp.Repositories.BusSpecRepository;
import ru.ndavs.atp.models.BusSpecs;

@Configuration
public class BusConfig {

    @Bean
    CommandLineRunner commandLineRunnerBus(BusSpecRepository repository) {
        return args -> {
            BusSpecs busSpecs = new BusSpecs();
            busSpecs.setModel("b_1");
            busSpecs.setNumber_of_sits(12);
            repository.save(busSpecs);
        };
    }
}
