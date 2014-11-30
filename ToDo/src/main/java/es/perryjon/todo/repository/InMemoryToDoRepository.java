package es.perryjon.todo.repository;

import es.perryjon.todo.model.ToDoItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryToDoRepository implements ToDoRepository {
    private AtomicLong currentId = new AtomicLong(); // thread-safe provider for unique id number
    private ConcurrentMap<Long, ToDoItem> toDos = new ConcurrentHashMap<Long,ToDoItem>();  // efficient in-memory structure to hold ToDo items.

    @Override
    public List<ToDoItem> findAll() {
        List<ToDoItem> toDoItems = new ArrayList<ToDoItem>(toDos.values());
        Collections.sort(toDoItems);   // sorts by number
        return toDoItems;
    }

    @Override
    public ToDoItem findById(Long id) {
        return toDos.get(id);
    }

    /*
    * Insert an item if not already in the map.
    */
    @Override
    public Long insert(ToDoItem toDoItem) {
        Long id = currentId.incrementAndGet();
        toDoItem.setId(id);
        toDos.putIfAbsent(id, toDoItem);  // adds if not already in the map
        return id;
    }

    /*
    * Replace existing ToDo item if it exists in the map
    */
    @Override
    public void update(ToDoItem toDoItem) {
        toDos.replace(toDoItem.getId(), toDoItem);
    }

    @Override
    public void delete(ToDoItem toDoItem) {
        toDos.remove(toDoItem.getId());
    }
}
