package org.project.personal.identity_provider.repository;

import org.junit.jupiter.api.Test;
import org.project.personal.identity_provider.entity.Member;
import org.project.personal.identity_provider.utils.MemberTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

@DataJpaTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void save(){
        Member member = MemberTestUtils.getMember();

        Member actual = memberRepository.save(member);

        assertThat(actual, not(nullValue()));
    }

    @Test
    void findAll(){
        memberRepository.save(MemberTestUtils.getMember());

        List<Member> actual = memberRepository.findAll();

        assertThat(actual, not(empty()));
    }

    @Test
    void update(){
         Member before = memberRepository.save(MemberTestUtils.getMember());

         entityManager.detach(before);

         Member after = Member.builder()
                 .id(before.getId())
                 .emailLocal(before.getEmailLocal())
                 .emailDomain(before.getEmailDomain())
                 .memberName("afterName")
                 .password(before.getPassword())
                 .build();

         memberRepository.save(after);

         Optional<Member> actual = memberRepository.findById(before.getId());

         actual.ifPresent(member -> assertThat(member.getMemberName(), not(equalTo(before.getMemberName()))));

    }

    @Test
    void delete(){
        Member member = memberRepository.save(MemberTestUtils.getMember());

        entityManager.detach(member);

        assertThat(memberRepository.existsById(member.getId()), is(true));

        memberRepository.deleteById(member.getId());

        entityManager.detach(member);

        assertThat(memberRepository.existsById(member.getId()), is(false));
    }
}