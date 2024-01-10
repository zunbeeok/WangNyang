package com.sparta.wangnyang.common.service

import com.sparta.wangnyang.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class CustomUserDetailService (
    private val userRepository:UserRepository,
            private val passwordEncoder : PasswordEncoder,

):UserDetailsService{
    override fun loadUserByUsername(loginId: String): UserDetails {
        return userRepository.findByLoginId(loginId)
                ?.let { createUserDeatils(it) }?:throw UsernameNotFoundException("해당 유저는 없습니다.")
    }

    private fun createUserDeatils(user:com.sparta.wangnyang.entity.User):UserDetails {
        return User(
                user.name,
                passwordEncoder.encode(user.pw),
                mutableListOf(SimpleGrantedAuthority("ROLE_MEMBER"))
        )
    }

}