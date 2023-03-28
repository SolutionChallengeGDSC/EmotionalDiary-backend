package com.gdsc.EmotionalDiary.domain.diary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdsc.EmotionalDiary.domain.BaseTimeEntity;
import com.gdsc.EmotionalDiary.domain.user.User;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "DIARY")
public class Diary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "TITLE", length = 28)
    private String title;

    @NotNull
    @Column(name = "CONTENT")
    private String content;

    @NotNull
    @Column(name = "DATE")
    private Date date;

    @NotNull
    @Column(name = "SCORE")
    private int score;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    private Diary(String title, String content, Date date, int score, User user) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.score = score;
        this.user = user;
    }

    public static final Diary newInstance(String title, String content, Date date, int score, User user) {
        return new Diary(title, content, date, score, user);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) { this.content = content; }

    public void setDate(Date date) { this.date = date; }

    public void setScore(int score) { this.score = score; }
}
