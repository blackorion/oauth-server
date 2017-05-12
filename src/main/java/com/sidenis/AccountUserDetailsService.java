package com.sidenis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Value("${test.security.user.password}")
    private String userPassword = "benspassword";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("ben".equals(username))
            return createUser(username);
        else
            throw new UsernameNotFoundException("no user found with name: " + username);
    }

    private User createUser(String username) {
        return new User(username, userPassword, true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
    }

}