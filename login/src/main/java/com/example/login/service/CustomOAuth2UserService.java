package com.example.login.service;

import com.example.login.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2UserService
        extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request)
        throws OAuth2AuthenticationException{

        OAuth2User oauthUser = super.loadUser(request);

        String provider =
                request.getClientRegistration().getRegistrationId();

        Map(String, Object) attributes = oauthUser.getAttributes();

        String name = "";
        String email = "";

        // Github User Data
    }
}
