package br.com.projetospring.forum.config

import br.com.projetospring.forum.security.JWTAUthenticationFilter
import br.com.projetospring.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
    ): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?){
        http?.
        csrf()?.disable()?.
        authorizeRequests()?.
        antMatchers("/topicos")?.hasAnyAuthority("LEITURA_ESCRITA")?.
        antMatchers(HttpMethod.POST, "/login")?.permitAll()?.
        anyRequest()?.
        authenticated()?.
        and()
        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.addFilterBefore(JWTAUthenticationFilter(jwtUtil = jwtUtil),UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncode(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncode())
    }
}