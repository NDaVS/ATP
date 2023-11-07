package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ndavs.atp.models.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);
    Optional<Users> findUserByLogin(String login);
}
