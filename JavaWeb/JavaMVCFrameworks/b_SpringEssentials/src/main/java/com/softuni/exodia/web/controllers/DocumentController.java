package com.softuni.exodia.web.controllers;

import com.softuni.exodia.domain.models.binding.DocumentCreateBindingModel;
import com.softuni.exodia.domain.models.service.DocumentServiceModel;
import com.softuni.exodia.domain.models.view.DocumentDetailsViewModel;
import com.softuni.exodia.domain.models.view.DocumentViewModel;
import com.softuni.exodia.services.DocumentService;
import com.softuni.exodia.utils.MapperUtil;
import com.softuni.exodia.utils.MdToHtmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.softuni.exodia.utils.Constants.SESSION_USERNAME_ATTRIBUTE;

@Controller
public class DocumentController extends BaseController<DocumentService> {

    private MdToHtmlParser parser;

    @Autowired
    public DocumentController(MapperUtil mapperUtil, DocumentService documentService, MdToHtmlParser parser) {
        super(mapperUtil, documentService);
        this.parser = parser;
    }

    @GetMapping("/schedule")
    public ModelAndView schedule(ModelAndView modelAndView, HttpSession session) {
        return super.getView(modelAndView,
                session.getAttribute(SESSION_USERNAME_ATTRIBUTE) != null ?
                        "schedule" :
                        "redirect:/");
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleConfirm(@ModelAttribute DocumentCreateBindingModel model, ModelAndView modelAndView) {

        DocumentServiceModel document = this.service.create(this.mapper.map(model, DocumentServiceModel.class));

        if (document == null) {
            throw new IllegalArgumentException("Scheduling failed");
        }

        return super.redirect(modelAndView, String.format("details/%s", document.getId()));
    }


    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {

        if ( session.getAttribute(SESSION_USERNAME_ATTRIBUTE) == null){
            return super.redirect(modelAndView, "login");
        } else {
            DocumentDetailsViewModel model = super.mapper.map(super.service.findBy("id", id), DocumentDetailsViewModel.class);
            return super.getView(modelAndView, "details", model);
        }
    }

    @GetMapping("/print/{id}")
    public ModelAndView print(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {

        if ( session.getAttribute(SESSION_USERNAME_ATTRIBUTE) == null){
            return super.redirect(modelAndView, "login");
        } else {
            DocumentDetailsViewModel model = super.mapper.map(super.service.findBy("id", id), DocumentDetailsViewModel.class);
            modelAndView.setViewName("print");
            modelAndView.addObject("model", model);
            return modelAndView;
        }
    }

    @PostMapping("/print/{id}")
    public ModelAndView printConfirm(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {

        if ( session.getAttribute(SESSION_USERNAME_ATTRIBUTE) == null){
            return super.redirect(modelAndView, "login");
        } else {
            return super.redirect(modelAndView, "pdf/{id}");
        }
    }

    @GetMapping("/pdf/{id}")
    public ModelAndView pdf(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession session) {

        if ( session.getAttribute(SESSION_USERNAME_ATTRIBUTE) == null){
            return super.redirect(modelAndView, "login");
        } else {

            DocumentViewModel doc = this.mapper.map(this.service.print(id), DocumentViewModel.class);
            String html = this.parser.parse(doc.getContent());
            modelAndView.addObject("title", doc.getTitle());
            modelAndView.addObject("content", html);
            modelAndView.setViewName("pdf");
            return modelAndView;
        }
    }
}
