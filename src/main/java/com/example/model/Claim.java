package com.example.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Claim {

    private String claimId;
    private String policyNumber;
    private String status;
    private LocalDate lossDate;
    private BigDecimal claimAmount;
    private String description;

    public Claim(String claimId,
                 String policyNumber,
                 String status,
                 LocalDate lossDate,
                 BigDecimal claimAmount,
                 String description) {

        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.status = status;
        this.lossDate = lossDate;
        this.claimAmount = claimAmount;
        this.description = description;

    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getLossDate() {
        return lossDate;
    }

    public void setLossDate(LocalDate lossDate) {
        this.lossDate = lossDate;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
