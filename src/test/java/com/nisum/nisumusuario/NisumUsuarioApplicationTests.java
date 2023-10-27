package com.nisum.nisumusuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class NisumUsuarioApplicationTests {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void main() {
        NisumUsuarioApplication.main(new String[] {"arg1", "arg2", "arg3"});
    }

}
