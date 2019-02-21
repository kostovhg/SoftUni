package org.softuni.sboj.service;

import org.modelmapper.ModelMapper;
import org.softuni.sboj.domain.entities.JobApplication;
import org.softuni.sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.sboj.repository.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplicationServiceModel) {
        return this.modelMapper.map(
                this.jobApplicationRepository
                        .save(this.modelMapper.map(
                                jobApplicationServiceModel, JobApplication.class
                        )), JobApplicationServiceModel.class);
    }

    @Override
    public List<JobApplicationServiceModel> getAllJobApplications() {
        return this.jobApplicationRepository.findAll()
                .stream()
                .map(ja -> this.modelMapper.map(ja, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationServiceModel getById(String id) {
        return this.modelMapper.map(
                this.jobApplicationRepository.findById(id), JobApplicationServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }
}
