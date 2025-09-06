package org.aemudapi.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Entity(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private boolean locked;
    private boolean forcePasswordChange = true;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Member member;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
