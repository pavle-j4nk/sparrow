package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.HotelDto;
import com.sparrow.backend.dto.HotelSearchDto;
import com.sparrow.backend.dto.NewHotelDto;
import com.sparrow.backend.dto.RoomSearchDto;
import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.HotelRepository;
import com.sparrow.backend.repository.HotelReservationRepository;
import com.sparrow.backend.service.HotelService;
import com.sparrow.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<HotelDto> findAllExtended() {
        List<Hotel> hotels = findAll();
        List<HotelDto> hotelDtos = new ArrayList<>();
        for (Hotel h : hotels) {
            HotelDto dto = new HotelDto();
            dto.setId(h.getId());
            dto.setName(h.getName());
            dto.setAddress(h.getAddress());
            dto.setDescription(h.getDescription());
            dto.setPriceLists(h.getPriceLists());
            dto.setRooms(h.getRooms());
            dto.setAdmin(h.getAdmin());
            dto.setHotelServices(h.getHotelServices());
            dto.setImage(h.getImage());
            hotelDtos.add(dto);
        }
        return hotelDtos;
    }

    @Override
    public Hotel getOne(Long id) {
        return hotelRepository.getOne(id);
    }

    @Override
    public Hotel findOne(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new HotelNotFoundException();
        }
    }

    @Override
    public Hotel findByName(String name) {
        Optional<Hotel> hotel = hotelRepository.findByName(name);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new HotelNotFoundException(name);
        }
    }

    @Override
    public Hotel findByAddress(String address) {
        Optional<Hotel> hotel = hotelRepository.findByAddress(address);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new HotelNotFoundException();
        }
    }

    public Hotel findById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new HotelNotFoundException(id);
        }
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel update(Hotel h) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(h.getId());
        Hotel hotel = hotelOptional.get();
        hotel.setName(h.getName());
        hotel.setDescription(h.getDescription());
        hotel.setAddress(h.getAddress());
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public Hotel create(NewHotelDto newHotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(newHotelDto.getName());
        hotel.setDescription(newHotelDto.getDescription());
        hotel.setAdmin(userService.findByUsername(newHotelDto.getUserEmail()));
        return hotel;
    }

    @Override
    public Hotel updateRoom(Long hotelId, Long roomId, Room room) {
        Hotel hotel = findById(hotelId);
        Set<Room> rooms = hotel.getRooms();
        for (Room r : rooms) {
            if (r.getId() == roomId) {
                r.setName(room.getName());
                r.setBalcony(room.isBalcony());
                r.setBedsNo(room.getBedsNo());
                r.setFloor(room.getFloor());
                break;
            }
        }
        return save(hotel);
    }

    @Override
    public List<Hotel> search(HotelSearchDto hotelSearchDto) {
        List<Hotel> hotels = hotelRepository.findAllByName(hotelSearchDto.getPlace().toUpperCase());

        List<HotelReservation> reservations = hotelReservationRepository.findAll();

        for (HotelReservation r : reservations) {
            if ((hotelSearchDto.getStart().before(r.getStart()) || hotelSearchDto.getStart().equals(r.getStart())) &&
                    (hotelSearchDto.getEnd().after(r.getEnd()) || hotelSearchDto.getEnd().equals(r.getEnd()))) {
                for (Room room : r.getRooms()) {
                    hotels.remove(room.getHotel());
                    break;
                }
            }
        }
        return hotels;
    }

    @Override
    public Set<PriceListItem> searchRooms(RoomSearchDto roomSearchDto, Long hotelId) {
        Hotel hotel = findById(hotelId);
        PriceList priceList = hotel.getPriceLists().iterator().next();
        Set<PriceListItem> priceListItems = priceList.getItems();

        List<HotelReservation> reservations = hotelReservationRepository.findByDate(roomSearchDto.getStart(), roomSearchDto.getEnd());

        for (HotelReservation reservation : reservations) {
            for (Room room : reservation.getRooms()) {
                priceListItems.removeIf(r -> r.getRoom().equals(room));
            }
        }
        Set<PriceListItem> withExactCapacity = new HashSet<>();

        priceListItems.removeIf(r -> r.getRoom().getBedsNo() < roomSearchDto.getCapacity());

        for (PriceListItem item : priceListItems) {
            if (item.getRoom().getBedsNo() == roomSearchDto.getCapacity()) {
                withExactCapacity.add(item);
            }
        }

        if (withExactCapacity.size() < roomSearchDto.getRooms()) {
            priceListItems.removeIf(r -> r.getRoom().getBedsNo() < roomSearchDto.getGuests());
            priceListItems.addAll(withExactCapacity);
        }

        return priceListItems;
    }


    @Override
    public Set<PriceListItem> searchRoomsByCurrentDate(Long hotelId) {
        Hotel hotel = findById(hotelId);
        PriceList priceList = hotel.getPriceLists().iterator().next();
        Set<PriceListItem> priceListItems = priceList.getItems();

        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        List<HotelReservation> hotelReservations = hotelReservationRepository.findByCurrentDate(currentDate);

        for (HotelReservation reservation : hotelReservations) {
            for (Room room : reservation.getRooms()) {
                priceListItems.removeIf(r -> r.getRoom().equals(room));
            }
        }

        return priceListItems;
    }
}
