package com.example.controller;

import com.example.model.Claim;
import com.example.model.ClaimCreateRequest;
import com.example.service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    // POST /claims
    @PostMapping
    //public ResponseEntity<Claim> createClaim(@RequestBody ClaimCreateRequest request) {
    public ResponseEntity<Claim> createClaim(@Valid @RequestBody ClaimCreateRequest request) {
        Claim createdClaim = claimService.createClaim(request);
        return ResponseEntity.status(201).body(createdClaim);

    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }
}