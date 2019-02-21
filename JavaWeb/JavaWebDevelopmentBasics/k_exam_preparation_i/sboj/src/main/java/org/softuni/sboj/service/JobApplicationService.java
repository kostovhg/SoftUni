package org.softuni.sboj.service;

import org.softuni.sboj.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplicationServiceModel);

    List<JobApplicationServiceModel> getAllJobApplications();

    JobApplicationServiceModel getById(String id);

    void delete(String id);

}
