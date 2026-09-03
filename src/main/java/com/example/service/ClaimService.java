package com.example.service;

import com.example.model.Claim;
import com.example.model.ClaimCreateRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClaimService {
    private int claimSequence = 100555;
    private final Map<String, Claim> claims = new HashMap<>();

    public ClaimService() {

        claims.put("CLM-100123",

                new Claim("CLM-100123",
                        "POL-456789",
                        "OPEN",
                        LocalDate.of(2026, 8, 15),
                        new BigDecimal("10000.50"),
                        "Water damage to kitchen"
                )
        );
        claims.put("CLM-100124",

                new Claim("CLM-100124",
                        "POL-456788",
                        "OPEN",
                        LocalDate.of(2026, 2, 10),
                        new BigDecimal("5000.50"),
                        "Roof is damaged because of high wind"
                )
        );
    }

    public Claim getClaimById(String claimId) {
        return claims.get(claimId);
    }


    public Claim createClaim(ClaimCreateRequest request) {

        String claimId = "CLM-" + claimSequence++;

        Claim claim = new Claim(
                claimId,
                request.getPolicyNumber(),
                "OPEN",
                request.getLossDate(),
                request.getClaimAmount(),
                request.getDescription()
        );

        claims.put(claimId, claim);

        return claim;

    }
}
