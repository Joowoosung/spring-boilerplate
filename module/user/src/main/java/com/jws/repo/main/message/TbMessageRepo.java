package com.jws.repo.main.message;

import com.jws.domain.main.message.TbMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TbMessageRepo extends JpaRepository<TbMessage, Long> {

  @Transactional
  @Modifying
  @Query("UPDATE TbMessage m SET m.isRead = 1 WHERE m.player.id = :playerId AND m.isRead = 0")
  void readAll(@Param("playerId") Long userId);

  @Query("SELECT count(m.id) FROM TbMessage m WHERE m.player.id = :playerId AND m.isRead = 0")
  Long myUnreadCount(@Param("playerId") Long userId);
}
