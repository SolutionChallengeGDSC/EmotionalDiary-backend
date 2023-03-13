package com.gdsc.EmotionalDiary.domain.user;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    private User(String email, String nickname, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public static final User newInstance(String email, String nickname, Role role) {
        return new User(email, nickname, role);
    }

    public final void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
