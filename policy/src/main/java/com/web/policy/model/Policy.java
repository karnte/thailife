package com.web.policy.model;
import jakarta.persistence.*;

@Entity
@Table(name = "benefit_master")
public class Policy {

    @Id
    @Column(name = "policy_no")
    private String policyNo;

    @Column(name = "policy_type")
    private String policyType;

    @Column(name = "status")
    private String status;

    @Column(name = "agent_id")
    private String agentId;
    
    public Policy() {
    }

    public Policy(String policyNo, String policyType, String status, String agentId) {
        this.policyNo = policyNo;
        this.policyType = policyType;
        this.status = status;
        this.agentId = agentId;
    }

    // Getters & Setters
    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}

