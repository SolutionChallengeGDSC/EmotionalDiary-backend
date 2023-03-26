package com.gdsc.EmotionalDiary.domain.todo;

import com.gdsc.EmotionalDiary.domain.user.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class TodoSpecification {
    public static Specification<Todo> equalUser(final User user) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user"), user
        );
    }

    public static Specification<Todo> inCategory(final List<String> category) {
        return (root, query, criteriaBuilder) -> root.get("category").in(category);
    }

    public static Specification<Todo> betweenGoalTime(final LocalDateTime startTime, final LocalDateTime endTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("goalTime"), startTime, endTime);
    }
}
