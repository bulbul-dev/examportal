package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println("Starting code.....");
            User user = new User();
            user.setFirstName("Bulbul");
            user.setLastName("Ahmed");
            user.setUsername("bdbulbul");
            user.setPassword(this.bCryptPasswordEncoder.encode("1234"));
            user.setEmail("bd@gmail.com");
            user.setPhone("01753155400");
            user.setProfile("default.png");

            Role role = new Role();
            role.setRoleId(44L);
            role.setRoleName("ADMIN");

            Set<UserRole> userRoleSet = new HashSet<>();

            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);

            userRoleSet.add(userRole);

            User user1 = this.userService.createUser(user, userRoleSet);
            System.out.println(user1.getUsername());

        } catch (UserFoundException e) {
            e.printStackTrace();
        }

    }

}
