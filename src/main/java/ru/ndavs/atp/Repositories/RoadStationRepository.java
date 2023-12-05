package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.CompositeKeys.RoadStationKey;
import ru.ndavs.atp.models.RoadStation;

public interface RoadStationRepository extends JpaRepository<RoadStation, RoadStationKey> {
}
