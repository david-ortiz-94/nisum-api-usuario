package com.nisum.nisumusuario.infraestructure.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {
        "jwt.secret=gSq2W5iPWQolLwgzoUj8Nv6unRoZf81gScvcu/nlI0nWKilKf2YyaTXELWQbLrNS0chsGfAmWx787NzVatBCXg==",
        "jwt.expirationMs=3600000"
})
class JwtTokenServiceTest {

    @Autowired
    private JwtTokenService jwtTokenService;


    @Test
    void generateTokenTest() {
        String username = "testUser";
        String token = jwtTokenService.generateToken(username);
        assertNotNull(token);
    }
}
