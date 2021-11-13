package com.example.mimicchio.security

import com.example.mimicchio.utils.Log.Companion.log
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.stereotype.Component

import javax.servlet.FilterChain

import javax.servlet.http.HttpServletResponse

import javax.servlet.http.HttpServletRequest

import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors


@Component
class JwtTokenFilter: OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtConfiguration: JwtConfiguration;

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {

        // Decoding with ByteArray to avoid packaging problems
        val parsedSecret = jwtConfiguration.secret?.toByteArray(charset("UTF-8"))

        try {
            val token: String? = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token == null) {
                chain.doFilter(request, response)
                return
            }
            val claimToken = Jwts.parserBuilder().setSigningKey(parsedSecret).build().parseClaimsJws(token)

            val authorities: List<String> = claimToken.body["authorities"] as ArrayList<String>
            val principal = User(
                    id = "hello",
                    username = claimToken.body["username"] as String,
                    authorities = authorities.stream().map { authority -> SimpleGrantedAuthority(authority) }.collect(Collectors.toList())
            )

            val authentication = UsernamePasswordAuthenticationToken(principal, null, principal.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(request, response)

        } catch (e: JwtException) {
            log.error(e.message)
            //don't trust the JWT!
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
            return
        }

    }

}
