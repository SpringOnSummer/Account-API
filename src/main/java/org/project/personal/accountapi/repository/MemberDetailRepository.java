package org.project.personal.accountapi.repository;

import org.project.personal.accountapi.entity.MemberDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {

}
