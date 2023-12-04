package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.BusSpecs;

public interface BusSpecRepository extends JpaRepository<BusSpecs, String> {
}
