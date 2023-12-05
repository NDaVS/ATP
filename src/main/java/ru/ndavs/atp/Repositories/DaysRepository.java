package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Days;

public interface DaysRepository extends JpaRepository<Days, Long> {
}
