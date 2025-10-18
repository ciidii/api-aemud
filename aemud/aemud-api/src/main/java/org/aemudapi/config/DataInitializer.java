package org.aemudapi.config;

import lombok.AllArgsConstructor;
import org.aemudapi.member.entity.ContactInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.PersonalInfo;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.user.entity.Role;
import org.aemudapi.user.entity.User;
import org.aemudapi.user.repository.RoleRepository;
import org.aemudapi.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("SUPER_ADMIN").isEmpty()) {
            Role superAdminRole = new Role();
            superAdminRole.setName("SUPER_ADMIN");
            roleRepository.save(superAdminRole);
        }

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        if (userRepository.findByUsername("superadmin").isEmpty()) {
            List<Role> superAdminRoles = new ArrayList<>();
            superAdminRoles.add(roleRepository.findByName("SUPER_ADMIN").get());
            superAdminRoles.add(roleRepository.findByName("ADMIN").get());
            superAdminRoles.add(roleRepository.findByName("USER").get());
            User superAdmin = new User();
            superAdmin.setUsername("superadmin");
            superAdmin.setPassword(passwordEncoder.encode("password"));
            superAdmin.setRoles(superAdminRoles);
            superAdmin.setForcePasswordChange(true);
            Member virtualMember = new Member();
            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setFirstname("Super");
            personalInfo.setName("Admin");
            virtualMember.setPersonalInfo(personalInfo);
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setEmail("superadmin782150000@aemud.org");
            contactInfo.setNumberPhone("782150000");
            virtualMember.setContactInfo(contactInfo);
            memberRepository.save(virtualMember);
            superAdmin.setMember(virtualMember);
            userRepository.save(superAdmin);
        }
    }
}
