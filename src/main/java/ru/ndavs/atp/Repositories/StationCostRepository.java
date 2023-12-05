package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.CompositeKeys.StationsCostKey;
import ru.ndavs.atp.models.StationCost;

public interface StationCostRepository extends JpaRepository<StationCost, StationsCostKey> {
}
