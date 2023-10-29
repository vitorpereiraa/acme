package com.isep.acme.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isep.acme.dtos.UserView;
import com.isep.acme.dtos.UserViewMapper;
import com.isep.acme.model.User;
import com.isep.acme.services.iRepositories.UserRepository;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepo;
    @Autowired
    private UserViewMapper userViewMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username - %s, not found", username)));
    }

    public UserView getUser(final Long userId){
        return userViewMapper.toUserView(userRepo.getById(userId));
    }

    public Optional<User> getUserId(Long user) {
        return userRepo.findById(user);
    }
}
