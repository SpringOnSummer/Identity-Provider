package org.project.personal.identity_provider.repository;

import org.project.personal.identity_provider.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
