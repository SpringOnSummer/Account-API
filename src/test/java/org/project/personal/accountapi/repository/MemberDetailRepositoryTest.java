package org.project.personal.accountapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.utils.MemberTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

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
                .memberId(before.getMemberId())
                .phoneNumber(before.getPhoneNumber())
                .nickName(before.getNickName())
                .joinedAt(before.getJoinedAt())
                .build();

        ReflectionTestUtils.setField(after,"id",100L);
        after.changePhoneNumber("010-9876-5432");

        entityManager.merge(after);

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