//package ru.ndavs.atp.Configs;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ru.ndavs.atp.Repositories.BusRepository;
//import ru.ndavs.atp.models.Bus;
//
//@Configuration
//public class BusConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunnerBus(BusRepository repository) {
//        return args -> {
//            Bus boobavos = new Bus(
//
//                    "( .)___( .)",
//                    "active"
//
//            );
//            repository.save(boobavos);
//        };
//    }
//}
