package softuni.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.todolist.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
