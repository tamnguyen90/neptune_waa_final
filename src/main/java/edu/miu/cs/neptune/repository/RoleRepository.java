package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Role;
import edu.miu.cs.neptune.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
