package softuni.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
