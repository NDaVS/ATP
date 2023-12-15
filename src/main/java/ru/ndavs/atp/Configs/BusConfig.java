package ru.ndavs.atp.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ndavs.atp.Repositories.BusSpecRepository;
import ru.ndavs.atp.models.BusSpecs;

import java.util.List;

@Configuration
public class BusConfig {

    @Bean
    CommandLineRunner commandLineRunnerBus(BusSpecRepository repository) {
        List<BusSpecs> specs = repository.findAll();
        if (!specs.isEmpty()) {
            return null;
        }

        return args -> {

            BusSpecs MAN = new BusSpecs();
            MAN.setModel("MAN");
            MAN.setNumber_of_sits(59);
            BusSpecs HAS = new BusSpecs();
            HAS.setModel("HAS");
            HAS.setNumber_of_sits(43);
            repository.saveAll(List.of(MAN, HAS));

        };
    }
}
