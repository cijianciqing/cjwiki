package cj.springboot.wiki.util.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CJBCryptPasswordTest {
    public static void main(String[] args) {
        String pass = "111222333";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String passHash = encoder.encode(pass);
        System.out.println(passHash);
        //$2a$10$gAmvBsJI3nsyo9aG/aM0iet/FFaJ.CYzUaL8ISFciAwCosxdvyrD6
        //$2a$10$eRDDoTob4MCEuUe8sPa.wOuv5OVgSVr0bPKC7N4NoMt2ebGdW1XPq

        final boolean matches = encoder.matches(pass, passHash);
        System.out.println(matches);

//        String passHash2="$2a$10$eRDDoTob4MCEuUe8sPa.wOuv5OVgSVr0bPKC7N4NoMt2ebGdW1XPq";
//        final boolean matches2 = encoder.matches(pass, passHash2);
//        System.out.println(matches2);
    }
}
