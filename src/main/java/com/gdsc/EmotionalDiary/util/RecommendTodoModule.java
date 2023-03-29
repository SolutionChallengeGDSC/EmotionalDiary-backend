package com.gdsc.EmotionalDiary.util;

import com.gdsc.EmotionalDiary.util.json.RecommendTodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendTodoModule {
    List<RecommendTodo> walkTodos = new ArrayList<>();
    List<RecommendTodo> eatTodos = new ArrayList<>();
    List<RecommendTodo> contentTodos = new ArrayList<>();
    List<RecommendTodo> careTodos = new ArrayList<>();
    List<RecommendTodo> travelTodos = new ArrayList<>();
    List<List<RecommendTodo>> todos = new ArrayList<>();

    public RecommendTodoModule () {
        walkTodos.add(new RecommendTodo("Walk at park","Walk"));
        walkTodos.add(new RecommendTodo("Walk at new place","Walk"));
        walkTodos.add(new RecommendTodo("Walk a 1 mile","Walk"));
        walkTodos.add(new RecommendTodo("Walk a 2 mile","Walk"));
        walkTodos.add(new RecommendTodo("Walk a 3 mile","Walk"));

        eatTodos.add(new RecommendTodo("Eat some food", "Eat"));
        eatTodos.add(new RecommendTodo("Try your favorite food", "Eat"));
        eatTodos.add(new RecommendTodo("Go to a restaurant with a Google rating of 4 or higher", "Eat"));
        eatTodos.add(new RecommendTodo("Make some Sandwich You've Never Had", "Eat"));
        eatTodos.add(new RecommendTodo("Make some dinner date", "Eat"));

        contentTodos.add(new RecommendTodo("Watch a recommended movie", "Content"));
        contentTodos.add(new RecommendTodo("Watch a classic movie", "Content"));
        contentTodos.add(new RecommendTodo("Listening to classical music", "Content"));
        contentTodos.add(new RecommendTodo("listening to loud music", "Content"));
        contentTodos.add(new RecommendTodo("Watch travel-type YouTube", "Content"));

        careTodos.add(new RecommendTodo("Book a therapy", "Care"));
        careTodos.add(new RecommendTodo("Join a book club", "Care"));
        careTodos.add(new RecommendTodo("Read your diary", "Care"));
        careTodos.add(new RecommendTodo("Make some new hobby", "Care"));
        careTodos.add(new RecommendTodo("Call your best frined", "Care"));

        travelTodos.add(new RecommendTodo("Planning an overseas trip","Travel"));
        travelTodos.add(new RecommendTodo("Walking east, no reason","Travel"));
        travelTodos.add(new RecommendTodo("Visit nearby attractions","Travel"));
        travelTodos.add(new RecommendTodo("Go to beach","Travel"));
        travelTodos.add(new RecommendTodo("Planning an overseas trip","Travel"));

        todos.add(walkTodos);
        todos.add(eatTodos);
        todos.add(contentTodos);
        todos.add(careTodos);
        todos.add(travelTodos);
    }


    public RecommendTodo getRecommendTodo(int score) {
        Random random = new Random();
        int pickList = random.nextInt(5);

        if(score < 50) {
            return recommendLowScore(todos.get(pickList));
        } else if(score < 90) {
            return recommendHighScore(todos.get(pickList));
        } else {
            return null;
        }
    }
    private RecommendTodo recommendLowScore(List<RecommendTodo> todoList) {
        Random random = new Random();
        int pickTodo = random.nextInt(4);
        return todoList.get(pickTodo);
    }

    private RecommendTodo recommendHighScore(List<RecommendTodo> todoList) {
        Random random = new Random();
        int pickTodo = random.nextInt(3)+2;
        if(pickTodo >= 5) {
            return null;
        }
        return todoList.get(pickTodo);
    }
}
