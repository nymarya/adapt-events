package br.com.adapt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.adapt.model.User;
import br.com.adapt.repository.UserRepository;
import br.com.adapt.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService();
        user = new User();
        user.setId(1);
        user.setName("Gustavo");
        user.setEmail("test@test.com");
        user.setPassword("123");

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByEmailAddress(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        final User result = userServiceUnderTest.findByEmailAdress(email);

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        User result = userServiceUnderTest.save(user);

        // Verify the results
        assertEquals(email, result.getEmail());
    }
}
