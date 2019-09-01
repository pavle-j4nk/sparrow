package com.sparrow.backend.service;

import com.sparrow.backend.model.Room;

public interface RoomService {
    Room findById(Long id);

    Room save(Room room);
}
