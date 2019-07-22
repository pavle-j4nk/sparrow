package com.sparrow.data;

import com.sparrow.model.Privilege;
import com.sparrow.model.Role;
import com.sparrow.model.hotel.Hotel;
import com.sparrow.model.user.User;
import com.sparrow.repository.hotel.HotelRepository;
import com.sparrow.repository.user.PrivilegeRepository;
import com.sparrow.repository.user.RoleRepository;
import com.sparrow.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
    private PrivilegeRepository privilegeRepository;

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
        user.setAddress("Unknown");
        user.setRole(roleUser);
        user.setEnabled(true);
        userRepository.save(user);

        User u1 = new User("pavle.jankovic","pavle.gp@gmail.com", "Pavle", "Jankovic", "Babanovacka bb", passwordEncoder.encode("123"), true, roleUser);
        User u2 = new User("marko.ristic","marko.ristic@gmail.com", "Marko", "Ristic", "Topolska 18", passwordEncoder.encode("123"), true, roleUser);
        User u3 = new User("aleksandar.vujasinovic","aleksandar.vujasinov@gmail.com", "Aleksandar", "Vujasinovic", "Laze Stajica 16", passwordEncoder.encode("123"), true, roleUser);
        User admin = new User("sysadmin","admin@admin.com","Bog","Boziji","Nebeska 12", passwordEncoder.encode("123"), true, adminRole);

        Hotel h1 = new Hotel("Plaza","The Plaza Hotel is a landmarked 20-story luxury hotel and condominium apartment building in the Midtown Manhattan neighborhood of Manhattan, New York City. It opened in 1907 and is now owned by Katara Hospitality.", u2);
        Hotel h2 = new Hotel("Holiday Inn", "Holiday Inn is a British-owned American brand of hotels, and a subsidiary of InterContinental Hotels Group. Founded as a U.S. motel chain, it has grown to be one of the world's largest hotel chains.", u3);
        Hotel h3 = new Hotel("Burj Al Arab", "An architectural wonder and one of the most famous hotels in the world, the Burj Al Arab is a Dubai icon.", u1);


        userRepository.saveAll(Arrays.asList(u1, u2, u3, admin));
        hotelRepository.saveAll(Arrays.asList(h1, h2, h3));

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
