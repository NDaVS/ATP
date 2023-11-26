package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.models.Bus;

import java.util.List;

@Service
@AllArgsConstructor
public class BusService {
    private final BusRepository busRepository;

    public List<Bus> getBuses() {
        try {
            return busRepository.findAll();
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить список автобусов: " + e.getMessage() + "\n" + e.getStackTrace() + "\n");
        }
    }
}
