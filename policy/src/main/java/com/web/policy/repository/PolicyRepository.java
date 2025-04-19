package com.web.policy.repository;


import com.web.policy.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {
    // add query method
    List<Policy> findByStatus(String status);
}
