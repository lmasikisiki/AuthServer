package com.apisec;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhencer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication auth) {
		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("organization", auth.getName());
		((DefaultOAuth2AccessToken) token).setAdditionalInformation(additionalInfo);
		return token;
	}

}
