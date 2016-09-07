package com.rucsok.user;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class UserRegistrationIntegrationTest {

}
