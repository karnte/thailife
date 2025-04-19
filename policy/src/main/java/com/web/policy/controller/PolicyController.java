package com.web.policy.controller;

import com.web.policy.model.Policy;
import com.web.policy.model.ApiResponse;
import com.web.policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyRepository repository;

    @GetMapping
    public ResponseEntity<ApiResponse<Policy[]>> getAllPolicies(
            @RequestParam(value = "messageId", required = false) String messageId
    ) {
        ApiResponse<Policy[]> response = new ApiResponse<>();
        response.setMessageId(messageId != null ? messageId : UUID.randomUUID().toString());
        response.setRequestDateTime(LocalDateTime.now());

        try {
            List<Policy> policies = repository.findAll();

            if (policies.isEmpty()) {
                response.setStatus(new ApiResponse.Status(204, "No policies found"));
                response.setResponseDateTime(LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }

            response.setData(policies.toArray(new Policy[0]));
            response.setStatus(new ApiResponse.Status(200, "Success"));
            response.setResponseDateTime(LocalDateTime.now());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new ApiResponse.Status(500, "Internal Server Error: " + e.getMessage()));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{policyNo}")
    public ResponseEntity<ApiResponse<Policy>> getPolicyById(
            @PathVariable String policyNo,
            @RequestParam(value = "messageId", required = false) String messageId
    ) {
        ApiResponse<Policy> response = new ApiResponse<>();
        response.setMessageId(messageId != null ? messageId : UUID.randomUUID().toString());
        response.setRequestDateTime(LocalDateTime.now());

        try {
            Policy policy = repository.findById(policyNo)
                    .orElseThrow(() -> new RuntimeException("Policy not found"));
            
            response.setData(policy);
            response.setStatus(new ApiResponse.Status(200, "Success"));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new ApiResponse.Status(500, "Internal Server Error: " + e.getMessage()));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Policy>> createPolicy(
            @RequestBody Policy policy,
            @RequestParam(value = "messageId", required = false) String messageId
    ) {
        ApiResponse<Policy> response = new ApiResponse<>();
        response.setMessageId(messageId != null ? messageId : UUID.randomUUID().toString());
        response.setRequestDateTime(LocalDateTime.now());

        try {
            Policy savedPolicy = repository.save(policy);
            response.setData(savedPolicy);
            response.setStatus(new ApiResponse.Status(201, "Policy created successfully"));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.setStatus(new ApiResponse.Status(500, "Internal Server Error: " + e.getMessage()));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{policyNo}")
    public ResponseEntity<ApiResponse<Policy>> updatePolicy(
            @PathVariable String policyNo,
            @RequestBody Policy updated,
            @RequestParam(value = "messageId", required = false) String messageId
    ) {
        ApiResponse<Policy> response = new ApiResponse<>();
        response.setMessageId(messageId != null ? messageId : UUID.randomUUID().toString());
        response.setRequestDateTime(LocalDateTime.now());

        try {
            Policy existing = repository.findById(policyNo)
                    .orElseThrow(() -> new RuntimeException("Policy not found"));
            
            existing.setPolicyType(updated.getPolicyType());
            existing.setStatus(updated.getStatus());
            existing.setAgentId(updated.getAgentId());
            
            Policy savedPolicy = repository.save(existing);
            response.setData(savedPolicy);
            response.setStatus(new ApiResponse.Status(200, "Policy updated successfully"));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new ApiResponse.Status(500, "Internal Server Error: " + e.getMessage()));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{policyNo}")
    public ResponseEntity<ApiResponse<Void>> deletePolicy(
            @PathVariable String policyNo,
            @RequestParam(value = "messageId", required = false) String messageId
    ) {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessageId(messageId != null ? messageId : UUID.randomUUID().toString());
        response.setRequestDateTime(LocalDateTime.now());

        try {
            if (!repository.existsById(policyNo)) {
                response.setStatus(new ApiResponse.Status(404, "Policy not found"));
                response.setResponseDateTime(LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            repository.deleteById(policyNo);
            response.setStatus(new ApiResponse.Status(200, "Policy deleted successfully"));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new ApiResponse.Status(500, "Internal Server Error: " + e.getMessage()));
            response.setResponseDateTime(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
