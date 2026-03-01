import com.example.login.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService
        extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request)
            throws OAuth2AuthenticationException {

        OAuth2User oauthUser = super.loadUser(request);

        String provider =
                request.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = oauthUser.getAttributes();

        String name = "";
        String email = "";

        // GitHub user data
        if(provider.equals("github")) {
            name = (String) attributes.get("login");
            email = (String) attributes.get("email");
        }

        // LinkedIn user data (basic example)
        if(provider.equals("linkedin")) {
            name = (String) attributes.get("localizedFirstName");
            email = "linkedin_user@email.com"; // LinkedIn email API needs extra call
        }

        Optional<User> userOptional =
                userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setProvider(provider);
            userRepository.save(user);
        }

        return oauthUser;
    }
}