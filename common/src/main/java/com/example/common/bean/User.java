package com.example.common.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String realname;
    private String college;
    private String email;

    private List<String> authoritiesStrs;

    //将GrantedAuthority对象以json序列化存储到redis中,无法反序列化得到对象,以中间变量authoritiesStrs存储到redis中,要得到原值从authoritiesStrs转换
    @JSONField(serialize = false)
    private Collection<GrantedAuthority> authorities;


    public User(String username, String password, List<String> authoritiesStrs) {
        this.username = username;
        this.password = password;
        this.authoritiesStrs = authoritiesStrs;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null)
            return authorities;
        List<GrantedAuthority> list = new ArrayList<>();
        for (String authoritiesStr : authoritiesStrs) {
            SimpleGrantedAuthority simpleGrantedAuthority
                    = new SimpleGrantedAuthority(authoritiesStr);
            list.add(simpleGrantedAuthority);
        }
        authorities = list;
        return authorities;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", college='" + college + '\'' +
                ", email='" + email + '\'' +
                ", authoritiesStrs=" + authoritiesStrs +
                ", authorities=" + authorities +
                '}';
    }
}
*/
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String realname;
    private String college;
    private String email;




    /*public User(String username, String password, List<String> authoritiesStrs) {
        this.username = username;
        this.password = password;
        this.authoritiesStrs = authoritiesStrs;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }*/


    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null)
            return authorities;
        List<GrantedAuthority> list = new ArrayList<>();
        for (String authoritiesStr : authoritiesStrs) {
            SimpleGrantedAuthority simpleGrantedAuthority
                    = new SimpleGrantedAuthority(authoritiesStr);
            list.add(simpleGrantedAuthority);
        }
        authorities = list;
        return authorities;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", college='" + college + '\'' +
                ", email='" + email + '\'' +
                ", authoritiesStrs=" + authoritiesStrs +
                ", authorities=" + authorities +
                '}';
    }
*/
}
