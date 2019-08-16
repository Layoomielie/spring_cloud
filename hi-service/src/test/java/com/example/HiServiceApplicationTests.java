package com.example;

import com.example.util.Cons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HiServiceApplicationTests {

    @Test
    public void contextLoads() {

        System.out.println(Cons.audiences);
        System.out.println(Cons.name);
    }

}
