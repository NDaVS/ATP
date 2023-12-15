package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.CompositeKeys.StationsKeyT;
import ru.ndavs.atp.models.StationTime;

public interface StationTimeRepository extends JpaRepository<StationTime, StationsKeyT> {
}
