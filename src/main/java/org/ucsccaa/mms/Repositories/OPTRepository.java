package org.ucsccaa.mms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ucsccaa.mms.domains.OPT;

@Repository
public interface OPTRepository extends JpaRepository<OPT,Long> {
}
