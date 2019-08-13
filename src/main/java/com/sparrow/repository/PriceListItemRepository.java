package com.sparrow.repository;

import com.sparrow.model.PriceListItem;
import com.sparrow.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long> {
    Set<PriceListItem> findAllByPriceListId(Long id);

    PriceListItem findByRoom(Room room);

    PriceListItem findByRoomName(String name);
}
