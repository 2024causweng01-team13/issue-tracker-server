package org.causwengteam13.issuetrackerserver.domain.user.repository;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
