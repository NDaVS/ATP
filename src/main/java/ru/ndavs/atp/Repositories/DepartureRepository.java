package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Departures;

import java.util.Date;
import java.util.List;

public interface DepartureRepository extends JpaRepository<Departures, Long> {
    public List<Departures> findDeparturesByDate(Date date);

    public List<Departures> findDeparturesByStatus(String status);
}
