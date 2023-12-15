package ru.ndavs.atp.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ndavs.atp.Repositories.UserRepository;
import ru.ndavs.atp.models.Users;

import java.util.List;

@Configuration
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
                        "directior",
                        "abobus",
                        "abobus"
                );
                repository.save(employee);
        };
    }
}
