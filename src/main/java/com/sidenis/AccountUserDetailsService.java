package com.sidenis;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AccountUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("trusted-user".equals(username))
            return new User(username, "test123", true, true, true, true, AuthorityUtils.createAuthorityList("USER", "ROLE_ADMIN"));
        else
            throw new UsernameNotFoundException("no user found with name: " + username);
    }

}