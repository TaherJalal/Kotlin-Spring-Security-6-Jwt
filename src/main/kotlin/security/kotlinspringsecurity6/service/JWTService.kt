package security.kotlinspringsecurity6.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTService {
    private val SECRET = "77397A244326462948404D635166546A576E5A7234753778214125442A472D4B"

    fun generateToken(email: String): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
            .setSubject(email)
            .setIssuedAt(Date()) // Use Kotlin's built-in Date() function
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24 * 3))
            .compact()
    }

    fun decode(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun isExpired(token: String): Boolean {
        return decode(token).expiration.before(Date())
    }

}