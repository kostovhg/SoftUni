package org.softuni.sboj.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.sboj.domain.models.view.JobApplicationViewModel;
import org.softuni.sboj.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("jobApplicationListBean")
@RequestScoped
public class JobApplicationListBean extends BaseBean {

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    private List<JobApplicationViewModel> jobApplications;

    public JobApplicationListBean() {
    }

    @Inject
    public JobApplicationListBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.setJobApplications(this.jobApplicationService.getAllJobApplications());
        this.getJobApplications().forEach(jasm -> jasm.setSector(jasm.getSector().toLowerCase()));
    }

    public List<JobApplicationViewModel> getJobApplications() {
        return this.jobApplicationService.getAllJobApplications().stream()
                .map(jas -> this.modelMapper.map(jas, JobApplicationViewModel.class))
                .collect(Collectors.toList());
    }
    
    public void setJobApplications(List<JobApplicationServiceModel> jobApplications) {
        this.jobApplications = jobApplications.stream()
        .map(jasm -> this.modelMapper.map(jasm, JobApplicationViewModel.class))
        .collect(Collectors.toList());
    }
}