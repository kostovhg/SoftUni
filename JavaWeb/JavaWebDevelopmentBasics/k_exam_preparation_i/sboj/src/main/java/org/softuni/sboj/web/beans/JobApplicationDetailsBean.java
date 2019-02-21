package org.softuni.sboj.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.sboj.domain.models.view.JobApplicationViewModel;
import org.softuni.sboj.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("jobApplicationDetailsBean")
@RequestScoped
public class JobApplicationDetailsBean extends BaseBean {

    private JobApplicationViewModel jobApplicationViewModel;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationDetailsBean() {
    }

    @Inject
    public JobApplicationDetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = ((HttpServletRequest)
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest()).getParameter("id");
        this.setJobApplication(id);
    }

    public JobApplicationViewModel getJobApplication() {
        return this.jobApplicationViewModel;
    }

    public void setJobApplication(String id) {
        this.jobApplicationViewModel = this.modelMapper
                .map(this.jobApplicationService.getById(id), JobApplicationViewModel.class);
    }

}