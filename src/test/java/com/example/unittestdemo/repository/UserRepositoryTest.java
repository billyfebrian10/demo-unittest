package com.example.unittestdemo.repository;

import com.example.unittestdemo.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void setUpUserTest(){
        User user = new User("test","test");
        userRepository.save(user);
        Optional<User> optionalUserTest = userRepository.findOneByUsername("test");

        assertEquals(true, optionalUserTest.isPresent());

        Optional<User> optionalUserTest2 = userRepository.findOneByUsername("test2");
        assertEquals(false, optionalUserTest2.isPresent());
    }

}
