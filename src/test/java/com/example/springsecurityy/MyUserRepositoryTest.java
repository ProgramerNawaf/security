package com.example.springsecurityy;

import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyUserRepositoryTest {

    @Autowired
    AuthRepository myUserRepository;

    MyUser user, user1, user2, user3;

    @BeforeEach
    void setUp() {

        user1 = new MyUser(null, "muha", "123", "ADMIN", null);
        user2 = new MyUser(null, "Abdu", "123", "CUSTOMER", null);
        user3 = new MyUser(null, "ahmed", "123", "CUSTOMER", null);
    }

    @Test
    public void findMyUserByUsername() {
        myUserRepository.save(user1);
        user = myUserRepository.findMyUserByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1);

    }

    @Test
    public void findMyUserById() {
        myUserRepository.save(user1);
        user = myUserRepository.findMyUsersById(user1.getId());
        Assertions.assertThat(user).isEqualTo(user1);

    }
}