package vn.alpaca.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.alpaca.demo.Domain.UserEntity;
import vn.alpaca.demo.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = getUserByEmail(email);
        UserDetails userDetails = User.withUsername(user.getEmail()).password(user.getPassword())
                .authorities(user.getRole()).build();
        return userDetails;
    }

    private UserEntity getUserByEmail(String email){
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("not found!");
        }
        return user.get();
    }

    public void createUser(UserEntity user){
        Optional<UserEntity> exist = userRepository.findByEmail(user.getEmail());
        if (exist.isPresent()){
            throw new IllegalStateException("Email existed!");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProvider(UserEntity.Provider.LOCAL);
        user.setRole("USER");
        userRepository.save(user);
    }

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }
}
