package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.CompositeKeys.RoadStationKey;
import ru.ndavs.atp.models.Group;
import ru.ndavs.atp.models.RoadStation;
import ru.ndavs.atp.models.Station;

import java.util.List;

public interface RoadStationRepository extends JpaRepository<RoadStation, RoadStationKey> {
    public List<RoadStation> deleteAllById(Long id);
    public List<RoadStation> findAllByStation(Station station);
    public List<RoadStation> findAllById(Long id);
}
