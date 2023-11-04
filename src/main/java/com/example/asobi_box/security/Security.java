package com.example.asobi_box.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class Security {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
				.apply(new TokenAuthenticationConfigurer()); // filter追加ではなくconfiguerの適用
		return http.build();
	}

	static class TokenAuthenticationConfigurer
			extends AbstractHttpConfigurer<TokenAuthenticationConfigurer, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// authenticationManagerのインスタンスを取得してfilterに設定する
			final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			AbstractPreAuthenticatedProcessingFilter tokenFilter = new TokenPreAuthenticatedProcessingFilter();
			tokenFilter.setAuthenticationManager(authenticationManager);
			http.addFilter(tokenFilter);
			super.configure(http);
		}
	}

	static class TokenPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
		@Override
		protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
			return request.getParameter("token");
		}

		@Override
		protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
			return "";
		}
	}

	@Bean
	PreAuthenticatedAuthenticationProvider tokenProvider() {
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService((PreAuthenticatedAuthenticationToken token) -> {
			System.out.println("Principal:" + token.getPrincipal());
			System.out.println("Credential:" + token.getCredentials());
			// ここでトークン（token.getPrincipal()）を使用し、ユーザ情報をどこかからか取得する。

			User user = new User("hoge", "", Collections.emptyList());
			return user;
		});
		return provider;
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
