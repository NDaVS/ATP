package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.CompositeKeys.GroupStationKey;
import ru.ndavs.atp.models.Group;
import ru.ndavs.atp.models.GroupStation;

import java.util.List;

public interface GroupStationRepository extends JpaRepository<GroupStation, GroupStationKey> {
    public List<GroupStation> deleteAllByGroup(Group group);
    public List<GroupStation> findAllByGroup(Group group);
}
