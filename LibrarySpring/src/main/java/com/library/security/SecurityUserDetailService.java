package com.library.security;

import com.library.dto.UserDTO;
import com.library.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    final
    UserRepository userRepository;

    public SecurityUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserDTO> user = userRepository.findByEmail(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Nor found: " + userName));
        return new com.library.security.SecurityUserDetails(user.get());
    }
}