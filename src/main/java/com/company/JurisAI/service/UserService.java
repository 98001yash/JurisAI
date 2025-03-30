package com.company.JurisAI.service;


import com.company.JurisAI.entities.User;
import com.company.JurisAI.exceptions.ResourceNotFoundException;
import com.company.JurisAI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     return userRepository.findByEmail(username)
             .orElseThrow(()->new ResourceNotFoundException("User not found with email: "+username));
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found  with id: "+id));
    }
}
