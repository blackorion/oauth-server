package com.sidenis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private TokenStore tokenStore;
    private AccessTokenConverter tokenConverter;

    public OAuth2Config(AuthenticationManager authenticationManager, TokenStore tokenStore, AccessTokenConverter tokenConverter) {
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.tokenConverter = tokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .accessTokenConverter(tokenConverter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("acme")
                .secret("acmesecret")
                .authorizedGrantTypes("password")
                .scopes("openid");
    }

}
