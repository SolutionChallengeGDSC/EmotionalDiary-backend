package com.gdsc.EmotionalDiary.domain.user;

import com.gdsc.EmotionalDiary.domain.todo.Todo;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Email
    @Column(name = "EMAIL", unique = true, nullable = false, length = 50)
    private String email;

    @Length(min = 2, max = 30)
    @Column(name = "NICKNAME", nullable = false, length = 30)
    private String nickname;

    @Column(name = "PICTURE")
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false, length = 10)
    private Role role;

    @Column(name = "DIARY_PASSWORD", length = 50)
    private String diaryPassword;

    @Column(name = "PASSWORD_HINT", length = 100)
    private String passwordHint;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Todo> todos = new ArrayList<>();

    private User(String email, String nickname, String picture, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.picture = picture;
        this.role = role;
    }

    public static final User newInstance(String email, String nickname, String picture, Role role) {
        return new User(email, nickname, picture, role);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPasswordAndHint(String diaryPassword, String passwordHint) {
        this.diaryPassword = diaryPassword;
        this.passwordHint = passwordHint;
    }
}
