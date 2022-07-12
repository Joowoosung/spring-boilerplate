package com.jws.repo.redis;

import com.jws.domain.redis.R_Session;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface R_SessionRepo extends CrudRepository<R_Session, String> {

  List<R_Session> findByPrincipal(String principal);

}
