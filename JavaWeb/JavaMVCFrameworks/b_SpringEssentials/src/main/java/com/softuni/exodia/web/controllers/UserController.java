package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.binding.UserLoginBindingModel;
import com.softuni.exodia.domain.models.binding.UserRegisterBindingModel;
import com.softuni.exodia.domain.models.service.UserServiceModel;
import com.softuni.exodia.services.UserService;
import com.softuni.exodia.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.softuni.exodia.utils.Constants.SESSION_USER_ID_ATTRIBUTE;
import static com.softuni.exodia.utils.Constants.SESSION_USERNAME_ATTRIBUTE;

@Controller
public class UserController extends BaseController<UserService> {

    private final UserService service;

    @Autowired
    public UserController(MapperUtil mapper, UserService service) {
        super(mapper);
        this.service = service;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        return super.getView(modelAndView, "register");
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        return super.getView(modelAndView, "login");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model, ModelAndView modelAndView){

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords does not match.");
        }

        if (this.service.create(this.mapper.map(model, UserServiceModel.class)) != null) {
            throw new IllegalArgumentException("Registration failed");
        }

        return super.redirect(modelAndView,"login");
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute UserLoginBindingModel model, ModelAndView modelAndView, HttpSession session) {

        if (this.service.getUserByUsername(model.getUsername())== null) {
            throw new IllegalArgumentException("There is no user with that username.");
        }

        UserServiceModel userModel = this.mapper.map(model, UserServiceModel.class);

        if (userModel == null) {
            throw new IllegalArgumentException("User login failed.");
        }

        session.setAttribute(SESSION_USER_ID_ATTRIBUTE, userModel.getId());
        session.setAttribute(SESSION_USERNAME_ATTRIBUTE, userModel.getUsername());

        return super.redirect(modelAndView, "home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.invalidate();

        return super.redirect(modelAndView);
    }
}
