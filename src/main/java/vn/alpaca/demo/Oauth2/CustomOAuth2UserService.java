package vn.alpaca.demo.Oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import vn.alpaca.demo.Domain.UserEntity;
import vn.alpaca.demo.Repositories.UserRepository;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {
    private final UserRepository userRepository;
    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        CustomOAuth2User customUser = new CustomOAuth2User(user);
        saveOAuth2User(customUser);
        return customUser;
    }
    private void saveOAuth2User(CustomOAuth2User user){
        Optional<UserEntity> exist = userRepository.findByEmail(user.getEmail());
        if (!exist.isPresent()) {
            UserEntity newUser = new UserEntity();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setRole("USER");
            newUser.setProvider(UserEntity.Provider.GOOGLE);
            userRepository.save(newUser);
        }
    }
}