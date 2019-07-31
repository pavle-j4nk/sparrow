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
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

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
        User admin = new User("sysadmin", "admin@admin.com", "Bog", "Boziji", "Nebeska 12", passwordEncoder.encode("123"), true, adminRole);

        Address a1 = new Address("Danila Kisa 44, 21000, Novi Sad", 45.24, 19.83);
        Address a2 = new Address("Brace Ribnikar 17, 21000, Novi Sad", 45.24, 19.83);
        Address a3 = new Address("Topolska 18, 11000, Beograd", 44.79, 20.47);
        Address a4 = new Address("Gavrila Principa 3, 11000, Beograd", 44.81, 20.45);
        addressRepository.saveAll(Arrays.asList(a1, a2, a3, a4));

        Hotel h1 = new Hotel("Plaza", "The Plaza Hotel is a landmarked 20-story luxury hotel and condominium apartment building in the Midtown Manhattan neighborhood of Manhattan, New York City. It opened in 1907 and is now owned by Katara Hospitality.", u2, a1);
        Hotel h2 = new Hotel("Holiday Inn", "Holiday Inn is a British-owned American brand of hotels, and a subsidiary of InterContinental Hotels Group. Founded as a U.S. motel chain, it has grown to be one of the world's largest hotel chains.", u3, a2);
        Hotel h3 = new Hotel("Burj Al Arab", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", u1, a3);
        hotelRepository.saveAll(Arrays.asList(h1, h2, h3));

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
        priceListItemRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
        priceListRepository.save(pl1);

        h1.setPriceLists(plazaPriceLists);
        hotelRepository.save(h1);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, admin));

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
