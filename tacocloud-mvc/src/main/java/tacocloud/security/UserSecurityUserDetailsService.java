package tacocloud.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacocloud.data.UserRepository;
import tacocloud.domain.User;

@Slf4j
@Service
public class UserSecurityUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;

    @Autowired
    public UserSecurityUserDetailsService (UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            log.info("loadUserByName method is called");
            return user;
        }
        throw new UsernameNotFoundException("User" + username + "not found");
    }
}
