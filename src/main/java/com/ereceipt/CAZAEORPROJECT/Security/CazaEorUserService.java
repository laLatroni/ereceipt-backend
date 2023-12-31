package com.ereceipt.CAZAEORPROJECT.Security;

import com.ereceipt.CAZAEORPROJECT.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CazaEorUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(CazaEorUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
