package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    @NotNull
    private String last_name;

    @Column(name = "father_name")
    @NotNull
    private String father_name;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "role")
    @NotNull
    private String role;

    @Column(name = "login")
    @NotNull
    private String login;

    @Column(name = "password")
    @NotNull
    private String password;


    public Users(String firstName, String lastName, String fatherName, String email, String role, String login, String password) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.father_name = fatherName;
        this.email = email;
        this.role = role;
        this.login = login;
        this.password = password;
    }

}
