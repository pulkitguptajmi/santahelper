package com.chekk.santahelper;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SantahelperApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SantahelperApplicationTests extends AbstractTestNGSpringContextTests {

    @Test
    void contextLoads() {
    }

}
