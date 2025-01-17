package org.aemudapi.entities;

import org.aemudapi.club.entity.Club;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.member.entity.Member;
import org.aemudapi.user.entity.Role;
import org.aemudapi.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Mock
    private Member member;
    @Mock
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        User user = new User();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "ROLE_ADMIN"));
        roles.add(new Role(2L, "ROLE_USER"));
        Commission commission = new Commission(1L, "Commission");
        List<Club> clubs = new ArrayList<>();
        clubs.add(new Club(1L, "Club Informatique"));
        clubs.add(new Club(1L, "Club Maths"));
    }

    @Test
    void testConstructorsWithAllAgrs() {
        //Given
        Long id = 1L;
        String username = "AEMUD-111";
        boolean locked = false;
        String password = "password";
        //When
        User createdUser = new User(1L, username, password, locked, member, roles);
        //Then
        assertThat(id).isEqualTo(createdUser.getId());
        assertThat(username).isEqualTo(createdUser.getUsername());
        assertThat(password).isEqualTo(createdUser.getPassword());
        assertThat(locked).isEqualTo(createdUser.isLocked());
        assertThat(member).isEqualTo(createdUser.getMember());
        assertThat(roles).isEqualTo(createdUser.getRoles());
    }

    @Test
    void testGetters() {
        //given
        Long id = 1L;
        String username = "AEMUD-111";
        boolean locked = false;
        String password = "password";
        //when
        User user1 = new User();
        user1.setId(id);
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setLocked(locked);
        user1.setMember(member);
        user1.setRoles(roles);
        //then
        assertThat(id).isEqualTo(user1.getId());
        assertThat(username).isEqualTo(user1.getUsername());
        assertThat(password).isEqualTo(user1.getPassword());
        assertThat(locked).isEqualTo(user1.isLocked());
        assertThat(member).isEqualTo(user1.getMember());
        assertThat(roles).isEqualTo(user1.getRoles());

    }
}