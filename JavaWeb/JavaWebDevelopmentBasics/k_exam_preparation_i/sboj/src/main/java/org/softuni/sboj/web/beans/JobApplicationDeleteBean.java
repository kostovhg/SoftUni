package org.softuni.sboj.web.beans;

import org.modelmapper.ModelMapper;
import org.primefaces.extensions.component.slideout.SlideOut;
import org.softuni.sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.sboj.domain.models.view.JobApplicationViewModel;
import org.softuni.sboj.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("jobApplicationDeleteBean")
@RequestScoped
public class JobApplicationDeleteBean extends BaseBean {

    private JobApplicationViewModel jobApplicationViewModel;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationDeleteBean() {
    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = ((HttpServletRequest)
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest()).getParameter("id");
        this.setJobApplicationViewModel(id);
    }

    public JobApplicationViewModel getJobApplicationViewModel() {
        return this.jobApplicationViewModel;
    }

    public void setJobApplicationViewModel(String id) {
        this.jobApplicationViewModel = this.modelMapper
                .map(this.jobApplicationService.getById(id), JobApplicationViewModel.class);
    }

    public void delete() {

        this.jobApplicationService.delete(this.jobApplicationViewModel.getId());

        this.redirect("/home");
    }
}