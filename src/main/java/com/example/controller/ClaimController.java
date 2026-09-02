package com.example.controller;

import com.example.model.Claim;
import com.example.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/claims")

public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<Claim> getClaimById(@PathVariable String claimId) {
            Claim claim = claimService.getClaimById(claimId);
            if (claim == null) {
                return ResponseEntity.notFound().build();
            }
        return ResponseEntity.ok(claim);
    }
}