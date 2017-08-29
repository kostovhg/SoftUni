package softuni.todolist.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    // Fields of the entity
    private Integer id;

    private String email;

    private String fullName;

    private String password;

    private Set<Role> roles;



    private Set<Todo> todos;

    // Custom constructor
    public User(String email, String fullName, String password) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;

        this.roles = new HashSet<>();
        this.todos = new HashSet<>();
    }

    // Empty constructor
    public User() {}

    // Getters and setters

    // @Id tells Hibernate that this field will be primary key
    @Id
    // generate value automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Column(name= "email", unique = true, nullable = false)
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Column(name = "fullName", nullable = false)
    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    @Column(name = "password", length = 60, nullable = false)
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    // EAGER means that the roles will be loaded together with the user
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public void addRole(Role role) { this.roles.add(role); }

    @OneToMany(mappedBy = "author")
    public Set<Todo> getTodos() { return todos; }

    public void setTodos(Set<Todo> todos) { this.todos = todos; }
}
