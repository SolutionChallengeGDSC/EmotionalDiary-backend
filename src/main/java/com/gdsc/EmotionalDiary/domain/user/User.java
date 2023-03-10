package com.gdsc.EmotionalDiary.domain.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

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

    @Column(name = "EMAIL", unique = true, nullable = false, length = 50)
    private String email;

    @Length(min = 2, max = 30)
    @Column(name = "NICKNAME", nullable = false, length = 30)
    private String nickname;

    @Column(name = "ROLE", nullable = false, length = 10)
    private Role role;

    private User(String email, String nickname, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public User newInstance(String email, String nickname, Role role) {
        return new User(email, nickname, role);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
