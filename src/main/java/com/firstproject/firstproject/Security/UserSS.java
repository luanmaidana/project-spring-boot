package com.firstproject.firstproject.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.firstproject.firstproject.domain.Roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails{

    private Integer id;
    private String username;
    private String senha;

    private Collection<? extends GrantedAuthority> authorities;


    public UserSS(){

    }

    public UserSS(Integer id, String username, String senha, List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.senha = senha;
        this.authorities = roles.stream().map(x -> new SimpleGrantedAuthority(x.getNome())).collect(Collectors.toList());
    }



    public Integer getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
