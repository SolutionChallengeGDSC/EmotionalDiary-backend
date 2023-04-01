package com.gdsc.EmotionalDiary.domain.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdsc.EmotionalDiary.domain.BaseTimeEntity;
import com.gdsc.EmotionalDiary.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TODO")
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @NotNull
    @Column(name = "GOAL", length = 100)
    private String goal;

    @NotNull
    @Column(name = "SUCCESS")
    private boolean success;

    @NotNull
    @Column(name = "CATEGORY", length = 100)
    private String category;

    @NotNull
    @Column(name = "GAOLTIME")
    private LocalDateTime goalTime;

    @NotNull
    @Column(name = "IS_RECOMMEND")
    private boolean isRecommend;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;
    private Todo(String goal, String category, LocalDateTime goalTime, boolean isRecommend, User user) {
        this.goal = goal;
        this.success = false;
        this.category = category;
        this.goalTime = goalTime;
        this.isRecommend = isRecommend;
        this.user = user;
    }

    public static final Todo newInstance(String goal,String category, LocalDateTime goalTime, boolean isRecommend, User user) {
        return new Todo(goal, category, goalTime, isRecommend, user);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCategory(String category) { this.category = category; }
}
