package com.mike.usermessages;

import com.mike.usermessages.repository.MessageRepository;
import com.mike.usermessages.repository.RoleRepository;
import com.mike.usermessages.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles({"test"})
@SpringBootTest
@ComponentScan(lazyInit = true)
abstract public class AbstractTest {

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected EntityManager entityManager;

    protected static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>(
        DockerImageName.parse("postgres:15.3")
            .asCompatibleSubstituteFor("postgres"));

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @BeforeAll
    public static void beforeAll() {
        POSTGRES.start();
    }

    @BeforeEach
    void clear() {
        messageRepository.deleteAll();
        entityManager.createNativeQuery("delete from users_roles");
        userRepository.deleteAll();
    }
}
