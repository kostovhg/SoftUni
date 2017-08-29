package softuni.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.todolist.entity.Todo;
import softuni.todolist.repository.TodoRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model){

        // TODO: change the repository or current method to
        // filter only the tasks that belong to current user
        List<Todo> todos = this.todoRepository.findAll();

        model.addAttribute("view", "home/index");
        model.addAttribute("todos", todos);

        return "base-layout";
    }
}
