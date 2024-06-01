package org.causwengteam13.issuetrackerserver.domain.user.repository;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
