package softuni.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.todolist.bindingModel.TodoBindingModel;
import softuni.todolist.entity.Todo;
import softuni.todolist.entity.User;
import softuni.todolist.repository.TodoRepository;
import softuni.todolist.repository.UserRepository;

@Controller
public class TodoController {

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

    @GetMapping("/todo/{id}")
    public String details(Model model, @PathVariable Integer id){

        if (!this.todoRepository.exists(id)){
            return "redirect:/";
        }
        Todo todo = this.todoRepository.findOne(id);

        model.addAttribute("todo", todo);
        model.addAttribute("view", "todo/details");

        return "base-layout";
    }

    @GetMapping("/todo/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(@PathVariable Integer id, Model model){
        if(!this.todoRepository.exists(id)){
            return "redirect:/";
        }
        Todo todo = this.todoRepository.findOne(id);

        model.addAttribute("view", "todo/edit");
        model.addAttribute("todo", todo);

        return "base-layout";
    }

    @PostMapping("/todo/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editProcess(@PathVariable Integer id, TodoBindingModel todoBindingModel){
        if(!this.todoRepository.exists(id)){
            return "redirect:/";
        }

        Todo todo = this.todoRepository.findOne(id);
        todo.setContent(todoBindingModel.getContent());
        todo.setTitle(todoBindingModel.getTitle());

        this.todoRepository.saveAndFlush(todo);

        return "redirect:/todo/" + todo.getId();
    }

    @GetMapping("/todo/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(Model model, @PathVariable Integer id){
        if(!this.todoRepository.exists(id)){
            return "redirect:/";
        }

        Todo todo = this.todoRepository.findOne(id);

        model.addAttribute("todo", todo);
        model.addAttribute("view", "todo/delete");

        return "base-layout";
    }

    @PostMapping("/todo/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteProcess(@PathVariable Integer id){
        if(!this.todoRepository.exists(id)){
            return "redirect:/";
        }

        Todo todo = this.todoRepository.findOne(id);

        this.todoRepository.delete(todo);

        return "redirect:/";
    }
}
