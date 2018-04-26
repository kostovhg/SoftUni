package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.BranchJSONImportDTO;
import org.softuni.ruk.model.entities.Branch;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BranchRepository;
import org.softuni.ruk.services.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;
    private ModelParser modelParser;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelParser modelParser) {
        this.branchRepository = branchRepository;
        this.modelParser = modelParser;
    }

    @Override
    public boolean create(BranchJSONImportDTO branchDto) {
        try {
            this.branchRepository.save(this.modelParser.convert(branchDto, Branch.class));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Branch findByName(String branchName) {
        return this.branchRepository.findByName(branchName);
    }
}
