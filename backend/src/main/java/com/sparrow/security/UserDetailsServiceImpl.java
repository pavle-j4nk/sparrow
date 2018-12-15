package com.sparrow.security;

import com.sparrow.model.Privilege;
import com.sparrow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.sparrow.model.User u = userRepository.findByEmail(s);
        if (u == null) {
            throw new UsernameNotFoundException("User does not exist.");
        }

        if (!u.getEnabled()) {
            throw new UsernameNotFoundException("User is disabled.");
        }

        return new User(u.getEmail(), u.getPassword(), getAuthorities(u.getRole().getPrivileges()));
    }

    private List<GrantedAuthority> getAuthorities(Collection<Privilege> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Privilege p : privileges) {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }

        return authorities;
    }
}
