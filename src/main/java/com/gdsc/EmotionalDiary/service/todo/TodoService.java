package com.gdsc.EmotionalDiary.service.todo;

import com.gdsc.EmotionalDiary.domain.todo.Todo;
import com.gdsc.EmotionalDiary.domain.todo.TodoRepository;
import com.gdsc.EmotionalDiary.domain.todo.TodoSpecification;
import com.gdsc.EmotionalDiary.domain.user.User;
import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import com.gdsc.EmotionalDiary.exception.NoDataException;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoSearchServiceRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoServiceRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoSetCategoryRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoSetGoalRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.respone.TodoSearchResultResponse;
import com.gdsc.EmotionalDiary.service.todo.dto.respone.TodoServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final TodoServiceResponse saveTodo(@Valid final TodoServiceRequest todoServiceRequest) {
        logger.info("Todo 생성");
        User user = userRepository.findByEmail(todoServiceRequest.getUserEmail()).orElseThrow(
                () -> new NoDataException("유저가 존재하지 않습니다."));

        Todo todo = todoRepository.save(Todo.newInstance(
                todoServiceRequest.getGoal(),
                todoServiceRequest.getCategory(),
                user
        ));
        return convertTodoResponse(todo);
    }

    public final TodoServiceResponse changeSuccessStatus(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NoDataException("Todo가 존재하지 않습니다."));
        todo.setSuccess(!todo.isSuccess());

        Todo modifyTodo = todoRepository.save(todo);
        return convertTodoResponse(modifyTodo);
    }

    public final TodoServiceResponse changeGoal(@Valid final TodoSetGoalRequest todoSetGoalRequest) {
        Todo todo = todoRepository.findById(todoSetGoalRequest.getId()).orElseThrow(() -> new NoDataException("Todo가 존재하지 않습니다."));
        todo.setGoal(todoSetGoalRequest.getGoal());

        Todo modifyTodo = todoRepository.save(todo);
        return convertTodoResponse(modifyTodo);
    }

    public final TodoServiceResponse changeCategory(@Valid final TodoSetCategoryRequest todoSetCategoryRequest) {
        Todo todo = todoRepository.findById(todoSetCategoryRequest.getId()).orElseThrow(() -> new NoDataException("Todo가 존재하지 않습니다."));
        todo.setCategory(todoSetCategoryRequest.getCategory());

        Todo modifyTodo = todoRepository.save(todo);
        return convertTodoResponse(modifyTodo);
    }

    public final boolean deleteTodo(final Long id) {
        todoRepository.deleteById(id);
        return true;
    }

    public final TodoSearchResultResponse getTodos(@Valid TodoSearchServiceRequest todoSearchServiceRequest) {
        User user = userRepository.findByEmail(todoSearchServiceRequest.getUserEmail()).orElseThrow(() -> new NoDataException("유저가 존재하지 않습니다."));

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if(todoSearchServiceRequest.isReverse()) {
            sortDirection = Sort.Direction.DESC;
        }

        PageRequest pageRequest = PageRequest.of(
                todoSearchServiceRequest.getStart(),
                todoSearchServiceRequest.getLimit(),
                Sort.by(sortDirection, "createdAt"));

        Specification<Todo> spec = Specification.where(TodoSpecification.equalUser(user));
        spec = spec.and(TodoSpecification.betweenCreatedAt(
                todoSearchServiceRequest.getStartTime(),
                todoSearchServiceRequest.getEndTime()
        ));

        if(!todoSearchServiceRequest.getCategories().isEmpty()) {
            spec = spec.and(TodoSpecification.inCategory(todoSearchServiceRequest.getCategories()));
        }

        Page<Todo> todoPage = todoRepository.findAll(spec, pageRequest);
        return TodoSearchResultResponse.newInstance(
                todoPage.hasNext(),
                todoPage.getNumberOfElements(),
                todoPage.getContent().stream().map(todo -> convertTodoResponse(todo)).collect(Collectors.toList())
        );
    }

    private TodoServiceResponse convertTodoResponse(Todo todo) {
        return TodoServiceResponse.of(
                todo.getId(),
                todo.getGoal(),
                todo.isSuccess(),
                todo.getCategory(),
                todo.getCreatedAt()
        );
    }
}
