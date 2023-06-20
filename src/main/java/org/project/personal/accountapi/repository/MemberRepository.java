package org.project.personal.accountapi.repository;

import org.project.personal.accountapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
