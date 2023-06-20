package org.project.personal.accountapi.repository;

import org.junit.jupiter.api.Test;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.utils.MemberTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.empty;

@ActiveProfiles("test")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void save(){
        Member member = MemberTestUtils.getMember();

        Member actual = memberRepository.save(member);

        assertThat(actual, not(nullValue()));
    }

    @Test
    void findAll(){
        List<Member> actual = memberRepository.findAll();

        assertThat(actual, not(empty()));
    }

    @Test
    void update(){
        Member before = memberRepository.findById(100L).orElseThrow();

        entityManager.detach(before);

        Member after = Member.builder()
                .emailLocal(before.getEmailLocal())
                .emailDomain(before.getEmailDomain())
                .memberName("afterName")
                .password(before.getPassword())
                .build();

        ReflectionTestUtils.setField(after, "id", before.getId());

        memberRepository.save(after);

        Optional<Member> actual = memberRepository.findById(before.getId());

        actual.ifPresent(member -> assertThat(member.getName(), not(equalTo(before.getName()))));

    }

    @Test
    void delete(){
        Member member = memberRepository.findById(200L).orElseThrow();

        entityManager.detach(member);

        memberRepository.deleteById(member.getId());

        boolean result = memberRepository.existsById(member.getId());

        assertThat(result, is(false));
    }
}