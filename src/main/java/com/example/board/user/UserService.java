package com.example.board.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepository.findByUsername(username).get();
    }

    public User save(UserDTO userDTO){
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("Save " + user);

        return userRepository.save(user);
    }

    public void deleteByUsername(String username){
        log.info("Delete : " + username);
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("LoadUserByUsername in UserService");

        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("There is no such User"));
    }
}
