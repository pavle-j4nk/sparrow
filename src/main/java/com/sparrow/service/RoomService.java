package com.sparrow.service;

import com.sparrow.model.Room;

public interface RoomService {
    Room findById(Long id);

    Room save(Room room);
}
