package com.jws.repo.main.user;

import com.jws.domain.main.user.JwsUser;
import com.jws.dto.user_converter.UserRole;
import com.jws.dto.user_converter.UserStatus;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JwsUserRepo extends JpaRepository<JwsUser, Long> {

  Optional<JwsUser> findByUsername(String username);

  boolean existsByUsername(String username);

  @Transactional
  @Modifying
  @Query("UPDATE JwsUser u SET u.status = :status WHERE u.id = :id")
  void updateStatus(@Param("id") Long id, @Param("status") UserStatus status);

}
