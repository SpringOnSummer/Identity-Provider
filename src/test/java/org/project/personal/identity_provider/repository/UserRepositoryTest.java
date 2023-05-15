package org.project.personal.identity_provider.repository;

import org.junit.jupiter.api.Test;
import org.project.personal.identity_provider.entity.User;
import org.project.personal.identity_provider.utils.UserTestUtils;
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
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void save(){
        User user = UserTestUtils.getUser();

        User actual = userRepository.save(user);

        assertThat(actual, not(nullValue()));
    }

    @Test
    void findAll(){
        userRepository.save(UserTestUtils.getUser());

        List<User> actual = userRepository.findAll();

        assertThat(actual, not(empty()));
    }

    @Test
    void update(){
         User before = userRepository.save(UserTestUtils.getUser());

         entityManager.detach(before);

         User after = User.builder()
                 .id(before.getId())
                 .emailLocal(before.getEmailLocal())
                 .emailDomain(before.getEmailDomain())
                 .userName("afterName")
                 .password(before.getPassword())
                 .build();

         userRepository.save(after);

         Optional<User> actual = userRepository.findById(before.getId());

         actual.ifPresent(user -> assertThat(user.getUserName(), not(equalTo(before.getUserName()))));

    }

    @Test
    void delete(){
        User user = userRepository.save(UserTestUtils.getUser());

        entityManager.detach(user);

        assertThat(userRepository.existsById(user.getId()), is(true));

        userRepository.deleteById(user.getId());

        entityManager.detach(user);

        assertThat(userRepository.existsById(user.getId()), is(false));
    }
}