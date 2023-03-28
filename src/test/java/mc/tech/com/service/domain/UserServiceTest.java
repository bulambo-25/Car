package mc.tech.com.service.domain;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

import mc.tech.com.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
class UserServiceTest {
    @Autowired
    private UserService service;
    User user_save,user23 ;

    @BeforeEach
    void BeforeEachTest()
    {

        user_save = new User("DRAY", "BULAMBO", "0814783125", "draybulambo45@gmail.com","dray@123");
        user23 = new User("Franck", "Kalengayi", "0612345167", "franck@gmail.com","dray@123");



    }

    @Test
    void a_create() {
        User savedUser = this.service.create(user_save);
        User savedUser1 = this.service.create(user23);
        System.out.println(savedUser);
        System.out.println(savedUser1);
        assertEquals(user_save.getEmail(), savedUser.getEmail());
        assertNotNull(savedUser);
        assertNotEquals(savedUser1,savedUser);
    }

    @Test
    void b_read() {
        Optional<User>optionalUser =this.service.read(1L);
        Optional<User> o=optionalUser;
        System.out.println("optionalUser::"+optionalUser);
        log.info("optionalUser::", optionalUser);
    }

    @Test
    void c_getUserByEmail() {

        User retrievedUser = this.service.getUserByEmail(user_save.getEmail());
        System.out.println("user was find Email::"+retrievedUser);
        log.info("user was find Email::" ,retrievedUser.toString());
        assertNotNull(retrievedUser);
    }


    @Test
    void d_delete() {
        this.service.delete(2L);
    }




//
//    @Test
//    void createUser() {
//        User savedUser = this.service.createUser(user_save);
//        log.info("", savedUser.toString());
//        assertEquals(user_save, savedUser);
//        assertNotNull(savedUser);
//    }
//
//    @Test
//    void getUserByEmail() {
//
//        User retrievedUser = this.service.getUserByEmail(user_save.getEmail());
//        log.info("user was find Email::" ,retrievedUser.toString());
//        assertNotNull(retrievedUser);
//    }
}