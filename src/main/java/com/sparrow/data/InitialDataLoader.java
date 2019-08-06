package com.sparrow.data;

import com.sparrow.model.*;
import com.sparrow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private PriceListItemRepository priceListItemRepository;

    @Autowired
    private ExtraServicesRepository extraServicesRepository;

    @Autowired
    private HotelServicesRepository hotelServicesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_HOTEL_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role hotelAdminRole = roleRepository.findByName("ROLE_HOTEL_ADMIN");

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("test@test.com");
        user.setUsername("test");
        user.setAddress("Unknown");
        user.setRole(roleUser);
        user.setEnabled(true);
        userRepository.save(user);

        User u1 = new User("pavle.jankovic", "pavle.gp@gmail.com", "Pavle", "Jankovic", "Babanovacka bb", passwordEncoder.encode("123"), true, roleUser);
        User u2 = new User("marko.ristic", "marko.ristic@gmail.com", "Marko", "Ristic", "Topolska 18", passwordEncoder.encode("123"), true, roleUser);
        User u3 = new User("aleksandar.vujasinovic", "aleksandar.vujasinov@gmail.com", "Aleksandar", "Vujasinovic", "Laze Stajica 16", passwordEncoder.encode("123"), true, roleUser);
        User hotelAdmin = new User("hotel.admin", "hotel_admin@sparrow.com", "Hotel", "Admin", "Hotel Admin Address 0", passwordEncoder.encode("123"), true, hotelAdminRole);
        User admin = new User("sysadmin", "admin@admin.com", "Bog", "Boziji", "Nebeska 12", passwordEncoder.encode("123"), true, adminRole);

        Address a1 = new Address("Danila Kisa 44, 21000, Novi Sad", 19.83, 45.24);
        Address a2 = new Address("Brace Ribnikar 17, 21000, Novi Sad", 19.83, 45.24);
        Address a3 = new Address("Topolska 18, 11000, Beograd", 20.47, 44.79);
        Address a4 = new Address("Gavrila Principa 3, 11000, Beograd", 20.45, 44.81);
        addressRepository.saveAll(Arrays.asList(a1, a2, a3, a4));

        Hotel h1 = new Hotel("Plaza", "The Plaza Hotel is a landmarked 20-story luxury hotel and condominium apartment building in the Midtown Manhattan neighborhood of Manhattan, New York City. It opened in 1907 and is now owned by Katara Hospitality.", hotelAdmin, a1);
        Hotel h2 = new Hotel("Holiday Inn", "Holiday Inn is a British-owned American brand of hotels, and a subsidiary of InterContinental Hotels Group. Founded as a U.S. motel chain, it has grown to be one of the world's largest hotel chains.", hotelAdmin, a2);
        Hotel h3 = new Hotel("Burj Al Arab", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", hotelAdmin, a3);
        Hotel h4 = new Hotel("Sheratton", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", hotelAdmin, a4);
        Hotel h5 = new Hotel("City", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", hotelAdmin, a4);
        Hotel h6 = new Hotel("Nacional", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", hotelAdmin, a4);
        Hotel h7 = new Hotel("Mr.Jones", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", hotelAdmin, a4);
        Hotel h8 = new Hotel("Hilton", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", hotelAdmin, a4);
        hotelRepository.saveAll(Arrays.asList(h1, h2, h3, h4, h5, h6, h7, h8));

        ExtraService e1 = new ExtraService();
        e1.setName("Wellness");
        ExtraService e2 = new ExtraService();
        e2.setName("Swimming pool");
        ExtraService e3 = new ExtraService();
        e3.setName("Spa");
        ExtraService e4 = new ExtraService();
        e4.setName("Breakfast");
        ExtraService e5 = new ExtraService();
        e5.setName("Transfer to airport");
        ExtraService e6 = new ExtraService();
        e6.setName("Transfer from airport");
        ExtraService e7 = new ExtraService();
        e7.setName("Gym");
        extraServicesRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        HotelServices hs1 = new HotelServices();
        hs1.setExtraService(e1);
        hs1.setHotel(h1);
        hs1.setPrice(20.0);

        HotelServices hs2 = new HotelServices();
        hs2.setExtraService(e2);
        hs2.setHotel(h1);
        hs2.setPrice(5.0);

        HotelServices hs3 = new HotelServices();
        hs3.setExtraService(e3);
        hs3.setHotel(h1);
        hs3.setPrice(10.0);

        hotelServicesRepository.saveAll(Arrays.asList(hs1, hs2, hs3));

        Room r1 = new Room("Single room", 1, h1, 1, true);
        Room r2 = new Room("Double room", 2, h1, 1, true);
        Room r3 = new Room("Special double room", 3, h1, 1, true);
        Room r4 = new Room("Family room", 5, h1, 2, true);
        Room r5 = new Room("Small family room", 4, h1, 2, true);
        Room r6 = new Room("King's Apartment", 7, h1, 3, true);
        roomRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6));

        Set<PriceList> plazaPriceLists = new HashSet<>();
        PriceList pl1 = new PriceList(h1);
        priceListRepository.save(pl1);

        PriceListItem p1 = new PriceListItem(r1, 100.0, pl1);
        PriceListItem p2 = new PriceListItem(r2, 120.0, pl1);
        PriceListItem p3 = new PriceListItem(r3, 130.0, pl1);
        PriceListItem p4 = new PriceListItem(r4, 140.0, pl1);
        PriceListItem p5 = new PriceListItem(r5, 150.0, pl1);
        PriceListItem p6 = new PriceListItem(r6, 160.0, pl1);

        List<PriceListItem> plitems = Arrays.asList(p1, p2, p3, p4, p5, p6);
        priceListItemRepository.saveAll(plitems);
        pl1.setItems(new HashSet<>(plitems));
        priceListRepository.save(pl1);

        h1.setPriceLists(new HashSet<>(Arrays.asList(pl1)));
        hotelRepository.save(h1);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, admin, hotelAdmin));

        alreadySetup = true;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
