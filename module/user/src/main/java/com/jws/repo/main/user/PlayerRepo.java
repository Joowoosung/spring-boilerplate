package com.jws.repo.main.user;

import com.jws.domain.main.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

  boolean existsByNickAndUser(String username);

  boolean existsByPhoneAndUser(String phone);

  @Transactional
  @Modifying
  @Query("UPDATE Player p SET p.nick = :nick WHERE p.id = :id")
  void updateNick(@Param("id") Long id, @Param("nick") String nick);

  @Transactional
  @Modifying
  @Query("UPDATE Player p SET p.nick = :nick, p.email = :email, p.phone = :phone WHERE p.id = :id")
  void updateUserInfo(@Param("id") Long id, @Param("nick") String nick, @Param("email") String email, @Param("phone") String phone);

}

