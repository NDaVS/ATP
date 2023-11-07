package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
