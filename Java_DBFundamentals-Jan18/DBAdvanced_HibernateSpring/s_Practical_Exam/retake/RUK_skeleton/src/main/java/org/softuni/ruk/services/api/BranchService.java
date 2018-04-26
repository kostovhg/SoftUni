package org.softuni.ruk.services.api;

import org.softuni.ruk.model.dto.BranchJSONImportDTO;
import org.softuni.ruk.model.entities.Branch;

public interface BranchService {
    boolean create(BranchJSONImportDTO car);

    Branch findByName(String branchName);
}
