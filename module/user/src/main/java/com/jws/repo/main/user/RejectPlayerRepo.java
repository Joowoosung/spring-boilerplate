package com.jws.repo.main.user;

import com.jws.domain.main.user.RejectPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectPlayerRepo extends JpaRepository<RejectPlayer, Long> {

}
