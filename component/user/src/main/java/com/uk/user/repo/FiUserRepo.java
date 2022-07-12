package com.uk.user.repo;

import com.uk.user.domain.FiUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiUserRepo extends JpaRepository<FiUser, Long> {

  Optional<FiUser> findByUsername(String username);
}
