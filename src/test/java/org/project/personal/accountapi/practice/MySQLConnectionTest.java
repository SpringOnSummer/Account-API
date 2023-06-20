package org.project.personal.accountapi.practice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.personal.accountapi.config.DatabaseConfig;
import org.project.personal.accountapi.config.JpaConfig;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.proprety.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * 1. DataSource AutoConfiguration 제외하기
 * 2. Entity 관리를 위한 Entity Manager Factory
 * 3. Entity Manager 가 관리할 Entity 설정하기 (package)
 * 4. Entity Manager 에 사용할 데이터 베이스 속성 지정하기
 * 5. @Transactional 어노테이션 사용을 위해 Platform Transaction Manager 추가
 * 6. Connection 가져오기 위한 Datasource 설정
 * 7. 사용할 JPA Repository 범위 설정
 */

@ActiveProfiles("mysql")
@ContextHierarchy(
        @ContextConfiguration(classes =
                {
                        JpaConfig.class,
                        DatabaseConfig.class,
                        DatabaseProperties.class
                })
)
@DataJpaTest
class MySQLConnectionTest {

    @Autowired
    TestEntityManager entityManager;

    /**
     *  실습용 테스트이므로 확인시에만 @Test 활성
     */
//    @Test
    @DisplayName("Spring Datasource 및 MySQL 설정 정보 확인")
    void testConnection() {

        Member member = entityManager.find(Member.class, 100L);

        assertThat(member, notNullValue());

    }
}
