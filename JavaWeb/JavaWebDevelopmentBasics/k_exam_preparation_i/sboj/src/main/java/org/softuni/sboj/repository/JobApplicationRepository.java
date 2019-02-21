package org.softuni.sboj.repository;

import org.softuni.sboj.domain.entities.JobApplication;

public interface JobApplicationRepository extends GenericRepository<JobApplication, String> {

    void delete(String id);
}
