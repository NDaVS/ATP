package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Group;
import ru.ndavs.atp.models.Road;

public interface RoadRepository extends JpaRepository<Road, Long> {

}
