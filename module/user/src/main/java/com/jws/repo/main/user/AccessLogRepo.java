package com.jws.repo.main.user;

import com.jws.domain.main.user.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepo extends JpaRepository<AccessLog, Long> {

}
