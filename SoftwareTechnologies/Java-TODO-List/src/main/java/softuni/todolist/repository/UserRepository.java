package softuni.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.todolist.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
