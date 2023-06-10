package com.mike.usermessages;

import com.mike.usermessages.controller.AuthController;
import com.mike.usermessages.controller.MessageController;
import com.mike.usermessages.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserMessagesApplicationTests {

    @Autowired
    private  MessageController messageController;

    @Autowired
    private UserController userController;

    @Autowired
    private AuthController authController;

    @Test
    void contextLoadsMessage() {
        assertThat(messageController).isNotNull();
    } @Test
    void contextLoadsUser() {
        assertThat(userController).isNotNull();
    } @Test
    void contextLoadsAuth() {
        assertThat(authController).isNotNull();
    }
}
