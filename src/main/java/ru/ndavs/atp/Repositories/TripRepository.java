package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
