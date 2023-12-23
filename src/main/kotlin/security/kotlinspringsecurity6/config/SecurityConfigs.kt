package security.kotlinspringsecurity6.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter
import security.kotlinspringsecurity6.filter.JwtFilter
import security.kotlinspringsecurity6.model.Role

@Configuration
@EnableWebSecurity
class SecurityConfigs(
     private val jwtFilter: JwtFilter,
    private val authProvider: AuthenticationProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .cors{it.disable()}
            .authorizeHttpRequests{
                it.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                it.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                it.requestMatchers("/api/user").hasAuthority(Role.USER.name)
                it.requestMatchers("/api/admin").hasAuthority(Role.ADMIN.name)
                it.anyRequest().authenticated()
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}