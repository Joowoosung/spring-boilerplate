package com.jws.repo.main.certi;

import com.jws.domain.main.certi.CertificateValue;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateValueRepo extends JpaRepository<CertificateValue, String> {

  CertificateValue findByKey(String certificationKey);

  void deleteByExpiredDateBefore(Date deleteDate);
}
