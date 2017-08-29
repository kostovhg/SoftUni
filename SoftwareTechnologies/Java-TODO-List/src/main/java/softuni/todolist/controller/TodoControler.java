package softuni.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.todolist.bindingModel.TodoBindingModel;
import softuni.todolist.entity.Todo;
import softuni.todolist.entity.User;
import softuni.todolist.repository.TodoRepository;
import softuni.todolist.repository.UserRepository;

@Controller
public class TodoControler {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/todo/create")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model){
        model.addAttribute("view", "todo/create");

        return "base-layout";
    }

    @PostMapping("/todo/create")
    @PreAuthorize("isAuthenticated()")
    public String createProcess(TodoBindingModel todoBindingModel){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findByEmail(user.getUsername());

        Todo todoEntity = new Todo(
                todoBindingModel.getTitle(),
                todoBindingModel.getContent(),
                userEntity
        );

        this.todoRepository.saveAndFlush(todoEntity);

        return "redirect:/";
    }
}
