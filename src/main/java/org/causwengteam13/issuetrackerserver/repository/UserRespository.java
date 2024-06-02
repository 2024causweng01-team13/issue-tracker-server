package org.causwengteam13.issuetrackerserver.repository;

import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRespository extends JpaRepository<User, Long>{
    boolean existsById(Long id);
    boolean existsByName(String name);

    User findByid(Long id);
} 