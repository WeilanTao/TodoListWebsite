package com.taoemily.mytodo;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void testBCrypt(){
        String code= BCrypt.hashpw("nimda", BCrypt.gensalt());
        System.out.println(code);
    }
}
