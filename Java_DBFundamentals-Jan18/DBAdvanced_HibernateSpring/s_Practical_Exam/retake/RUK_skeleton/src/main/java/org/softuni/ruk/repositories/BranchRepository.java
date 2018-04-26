package org.softuni.ruk.repositories;

import org.softuni.ruk.model.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Branch findByName(String branchName);
}
