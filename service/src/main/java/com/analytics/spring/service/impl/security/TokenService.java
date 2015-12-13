package com.analytics.spring.service.impl.security;

import com.analytics.spring.dao.impl.TokenRepository;
import com.analytics.spring.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Dmitry Natalenko on 22.05.2015.
 */
@Service
public class TokenService implements PersistentTokenRepository {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenRepository.createNewToken(new Token(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        tokenRepository.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Token token = tokenRepository.getTokenForSeries(seriesId);
        if (token == null) {
            return null;
        }
        return new PersistentRememberMeToken(token.getUsername(),
                token.getSeries(), token.getTokenValue(), token.getDate());
    }

    @Override
    public void removeUserTokens(String username) {
        tokenRepository.removeUserTokens(username);
    }
}
