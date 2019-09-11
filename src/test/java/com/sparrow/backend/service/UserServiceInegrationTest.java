package com.sparrow.backend.service;

import com.sparrow.backend.model.Role;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.FriendRequestRepository;
import com.sparrow.backend.repository.FriendshipRepository;
import com.sparrow.backend.repository.UserRepository;
import com.sparrow.backend.service.impl.UserServiceImpl;
import com.sparrow.backend.service.impl.user.FriendshipServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceInegrationTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendshipRepository friendshipRepository;

    @Mock
    private FriendRequestRepository friendRequestRepository;

    @InjectMocks
    private UserServiceImpl userSer;

    @InjectMocks
    private FriendshipServiceImpl friendshipService;


    @Test
    public void sendRequestTest() {
        User user = new User("123", "123", "123", "!23", "12", "123", true, new Role());
        user.setId(1l);
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        assertNotNull(userSer.findById(1l));
    }

}
