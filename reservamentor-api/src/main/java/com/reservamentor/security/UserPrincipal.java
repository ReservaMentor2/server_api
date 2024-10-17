package com.reservamentor.security;

import com.reservamentor.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
