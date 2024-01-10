package ru.ndavs.atp.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.ndavs.atp.Repositories.UserRepository;
import ru.ndavs.atp.models.Users;

import java.util.List;

@Configuration
@EnableAsync
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository){
        List<Users> users = repository.findAll();
        if (!users.isEmpty()){
            return null;
        }
        return args -> {
                Users employee = new Users(
                        "Abobus",
                        "banfuciy",
                        "Rozen",
                        "booba@boob.s",
                        "director",
                        "32c9a2ec9e0c4e3a4cc93012b2e72c04b2c395578dcc80b535e951b452eaf9a3",
                        "32c9a2ec9e0c4e3a4cc93012b2e72c04b2c395578dcc80b535e951b452eaf9a3"
                );
                repository.save(employee);
        };
    }
}
