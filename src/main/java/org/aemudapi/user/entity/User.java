package org.aemudapi.user.entity;

import jakarta.persistence.*;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean locked;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Member member;
    @ManyToMany()
    private List<Role> roles;

    public User() {
        //constructor without args
    }

    public User(Long id, String username, String password, boolean locked, Member member, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.member = member;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
