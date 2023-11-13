package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
