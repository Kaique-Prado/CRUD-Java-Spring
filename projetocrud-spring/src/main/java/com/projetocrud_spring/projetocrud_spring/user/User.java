package com.projetocrud_spring.projetocrud_spring.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SequenceGenerator(name = "seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_user")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;

    public User(UserRequest payload) {
        this.name= payload.name();
        this.age= payload.age();
    }
}
