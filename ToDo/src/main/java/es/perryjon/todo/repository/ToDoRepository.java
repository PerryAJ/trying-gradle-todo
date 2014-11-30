package es.perryjon.todo.repository;

import es.perryjon.todo.model.ToDoItem;
import java.util.Collection;
import java.util.List;

public interface ToDoRepository {
    List<ToDoItem> findAll();
    ToDoItem findById(Long id);
    Long insert(ToDoItem toDoItem);
    void update(ToDoItem toDoItem);
    void delete(ToDoItem toDoItem);
}
