package com.robokae.personalblog;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.robokae.personalblog.model.Post;
import com.robokae.personalblog.model.User;
import com.robokae.personalblog.repository.PostRepository;
import com.robokae.personalblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitializeDatabase implements CommandLineRunner  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // For development purposes
    @Value("${blog.admin.username}")
    private String adminUser;

    @Value("${blog.admin.password}")
    private String adminPassword;

    @Value("${blog.admin.role}")
    private String adminRole;
    
    @Override
    public void run(String... args) throws Exception {
//        userRepository.deleteAll();
//        postRepository.deleteAll();

        adminPassword = passwordEncoder.encode(adminPassword);
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(adminRole));
        User admin = new User(adminUser, adminPassword, authorities);

        Post post = Post.builder()
                .title("My First Post")
                .publishDate(new Date())
                .author("Alexander Hom")
                .body("This is my first post!")
                .build();

        userRepository.save(admin);
        postRepository.save(post);
    }
}
