package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.view.HomeDocumentsListViewModel;
import com.softuni.exodia.domain.models.view.DocumentViewModel;
import com.softuni.exodia.services.DocumentService;
import com.softuni.exodia.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.softuni.exodia.utils.Constants.SESSION_USERNAME_ATTRIBUTE;

@Controller
public class HomeController extends BaseController<DocumentService> {

    @Autowired
    public HomeController(MapperUtil mapper, DocumentService documentService) {
        super(mapper, documentService);
    }

    @GetMapping("/")
    public ModelAndView getIndexView(ModelAndView modelAndView, HttpSession session) {

        return super.getView(modelAndView,
                session.getAttribute(SESSION_USERNAME_ATTRIBUTE) != null ?
                        "redirect:/home" :
                        "index");
    }

    @GetMapping("/home")
    public ModelAndView getHomeView(ModelAndView modelAndView, HttpSession session) {

        if ( session.getAttribute(SESSION_USERNAME_ATTRIBUTE) == null){
            return super.redirect(modelAndView, "login");
        } else {
            List<DocumentViewModel> documents = super.mapper.map(super.service.findAll(), DocumentViewModel.class);
            HomeDocumentsListViewModel model = new HomeDocumentsListViewModel(documents);
            modelAndView.setViewName("home");
            modelAndView.addObject("model", model);

            return modelAndView;
        }
    }
}
