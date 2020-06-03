package com.example.TLBkadai;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Order(1)//config複数の場合は@Orderで順位付け、若い数字のが強力
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	      .anyRequest().authenticated()
	      .and()
	      // ログアウト完了後にログイン画面を正しく表示するために必要（permitAllするのがポイント）
	      .logout().permitAll()
	      .and()
	      .oauth2Login()
	      // ログイン画面を表示するためのパスを指定（permitAllするのもポイント）
	      .loginPage("/login").permitAll();
	}

}