package ru.ndavs.atp.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ndavs.atp.Repositories.DaysRepository;
import ru.ndavs.atp.models.Days;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DaysConfig {
    @Bean
    CommandLineRunner commandLineRunnerDays(DaysRepository repository) {
        List<Days> days = repository.findAll();
        if (!days.isEmpty()) {
            return null;
        }
        return args -> {

            Days sun = new Days("sunday");
            Days mon = new Days("monday");
            Days tue = new Days("tuesday");
            Days wen = new Days("wednesday");
            Days thu = new Days("thursday");
            Days fri = new Days("friday");
            Days sat = new Days("saturday");
            repository.saveAll(Arrays.asList(sun, mon, tue, wen, thu, fri, sat));

        };
    }
}
