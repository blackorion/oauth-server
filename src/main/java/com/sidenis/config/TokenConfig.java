package com.sidenis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    @Value("${security.oauth.signing:test123}")
    private String signingKey;

    @Bean
    public AccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);

        return converter;
    }

    @Bean
    public TokenStore tokenStore(AccessTokenConverter converter) {
        return new JwtTokenStore((JwtAccessTokenConverter) converter);
    }

}
