package com.sparrow.backend.service;

import com.sparrow.backend.model.Room;
import com.sparrow.backend.repository.RoomRepository;
import com.sparrow.backend.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoomServiceTest {

    public static final String ROOM = "Room";
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private Room room;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void testFindById() {
        room = new Room();
        room.setId(1L);
        room.setBalcony(true);
        room.setBedsNo(1);
        room.setFloor(1);
        room.setName(ROOM);

        when(roomRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(room));
        Room result = roomService.findById(1L);
        assertEquals(result.getName(), room.getName());
    }

    @Test
    public void testSave() {
        when(roomRepository.save(room)).thenReturn(room);
        Room res = roomService.save(room);
        assertEquals(res, room);
    }
}
