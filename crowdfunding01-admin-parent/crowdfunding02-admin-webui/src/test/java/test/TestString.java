package test;

import org.fall.util.CrowdUtil;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestString {

    //测试MD5加密
    @Test
    public void testMd5(){
        String source = "fall";
        String md5 = CrowdUtil.md5(source);
        System.out.println(md5);
    }

    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 账号+盐 -》md5 -》 密码
        String encode = passwordEncoder.encode("admin");
        System.out.println(encode);
        // $2a$10$0XIIqD8m/8rV0HGl5eLOIeaI5UtCMftv6GvNl4qQZfptchOeC6zJa
        // $2a$10$UznulHbgI3IsxJeVnlCYP.RPY/V/Txq0rZQGCmqSuQ7Vvdu736hs6
    }

}
