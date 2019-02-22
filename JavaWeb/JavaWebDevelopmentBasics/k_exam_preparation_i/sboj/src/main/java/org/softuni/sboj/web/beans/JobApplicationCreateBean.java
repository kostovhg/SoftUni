package org.softuni.sboj.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.sboj.domain.models.binding.JobApplicationBindingModel;
import org.softuni.sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.sboj.service.JobApplicationService;
import org.softuni.sboj.util.Sector;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationCreateBean")
@RequestScoped
public class JobApplicationCreateBean extends BaseBean {

    private JobApplicationBindingModel jobApplicationBindingModel;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationCreateBean() {
    }

    @Inject
    public JobApplicationCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.jobApplicationBindingModel = new JobApplicationBindingModel();
    }

    public JobApplicationBindingModel getJobApplicationBindingModel() {
        return this.jobApplicationBindingModel;
    }

    public void setJobApplicationBindingModel(JobApplicationBindingModel jobApplicationBindingModel) {
        this.jobApplicationBindingModel = jobApplicationBindingModel;
    }

    public void register() {

        JobApplicationServiceModel jobApplicationServiceModel = this.jobApplicationService.createJobApplication(
                this.modelMapper.map(
                        this.jobApplicationBindingModel, JobApplicationServiceModel.class));

        this.redirect("/home");
    }

    
}