package com.sparrow.repository;

import com.sparrow.model.Hotel;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HotelRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void whenFindByName_thenReturnHotel() {
        Hotel hotel = new Hotel("foo", "bar", null, null);
        entityManager.persist(hotel);
        entityManager.flush();

        Hotel foundHotel = hotelRepository.findByName(hotel.getName()).get();

        MatcherAssert.assertThat("Not equal", foundHotel.getName().equals(hotel.getName()));
    }
}
