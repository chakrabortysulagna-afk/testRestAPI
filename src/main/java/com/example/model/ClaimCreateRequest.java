package com.example.model;


import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClaimCreateRequest {

    @NotBlank
    private String policyNumber;
    private LocalDate lossDate;
    private BigDecimal claimAmount;
    private String description;
    public ClaimCreateRequest() {

    }

    public String getPolicyNumber() {

        return policyNumber;

    }

    public void setPolicyNumber(String policyNumber) {

        this.policyNumber = policyNumber;

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
