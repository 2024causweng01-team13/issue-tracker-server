package org.causwengteam13.issuetrackerserver.domain.user.repository;

import java.util.Optional;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	Optional<User> findByLoginId(String loginId);
	boolean existsByLoginId(String loginId);
	boolean existsByName(String name);
}
