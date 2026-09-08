package com.example.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CircuitBreakerTestService {

    private final AtomicBoolean failing = new AtomicBoolean(true);
   // failing = true //set it true for failure testing
   // failing = false //set it false for success testing
    public String getData(String claimId) {

        if (failing.get()) {
            throw new RuntimeException("Dependency is DOWN");
        }
        return "Claim data successfully retrieved for " + claimId;
    }

    public void setFailing(boolean failing) {
        this.failing.set(failing);
    }

    public boolean isFailing() {
        return failing.get();
    }

}