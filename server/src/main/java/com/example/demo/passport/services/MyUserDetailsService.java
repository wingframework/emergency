package com.example.demo.passport.services;

import com.example.demo.Rbac.services.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserJpa userJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
      com.example.demo.Rbac.Entitys.User user =  userJpa.findByUsername(username);
      if(user==null){
          throw new UsernameNotFoundException("用户名不存在！");

      }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }


}
