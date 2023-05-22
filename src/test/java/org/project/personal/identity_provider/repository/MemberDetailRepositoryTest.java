package org.project.personal.identity_provider.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.entity.Member;
import org.project.personal.identity_provider.entity.MemberDetail;
import org.project.personal.identity_provider.utils.MemberTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@ActiveProfiles("test")
@DataJpaTest
class MemberDetailRepositoryTest {

    @Autowired
    MemberDetailRepository memberDetailRepository;

    @Autowired
    TestEntityManager entityManager;

    Member member;
    @BeforeEach
    void setUp() {
        member = MemberTestUtils.getMember();
        entityManager.persist(member);
    }

    @Test
    void save() {

        MemberDetail memberDetail = MemberTestUtils.getMemberDetail();
        ReflectionTestUtils.setField(memberDetail, "memberId",member.getId());

        MemberDetail actual = memberDetailRepository.save(memberDetail);

        assertThat(actual, notNullValue());
    }

    @Test
    void find() {

        List<MemberDetail> memberDetailList = memberDetailRepository.findAll();

        assertThat(memberDetailList, not(empty()));
    }

    @Test
    void update() {

        MemberDetail before = memberDetailRepository.findById(100L).orElseThrow();

        entityManager.detach(before);

        MemberDetail after = MemberDetail.builder()
                .id(before.getId())
                .memberId(before.getMemberId())
                .phoneNumber("010-9876-5432")
                .joinedAt(before.getJoinedAt())
                .build();

        memberDetailRepository.save(after);

        Optional<MemberDetail> actual = memberDetailRepository.findById(before.getId());

        actual.ifPresent(member -> assertThat(actual.get().getPhoneNumber(), not(equalTo(before.getPhoneNumber()))));

    }

    @Test
    void delete(){
        MemberDetail member = memberDetailRepository.findById(100L).orElseThrow();

        memberDetailRepository.deleteById(member.getId());

        boolean result = memberDetailRepository.existsById(member.getId());

        assertThat(result, is(false));
    }

}