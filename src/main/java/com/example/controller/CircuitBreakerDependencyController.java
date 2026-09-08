package com.example.controller;

import com.example.service.CircuitBreakerTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/dependency")

    /**
     * POST /dependency/down
     * POST /dependency/up
     * GET  /dependency/status
     */

    public class CircuitBreakerDependencyController {

        private final CircuitBreakerTestService circuitBreakerTestService;

        public CircuitBreakerDependencyController(CircuitBreakerTestService circuitBreakerTestService) {
            this.circuitBreakerTestService = circuitBreakerTestService;
        }

        @PostMapping("/up")

        public String bringUp() {
            circuitBreakerTestService.setFailing(false);
            return "Dependency is now UP";
        }

        @PostMapping("/down")
        public String bringDown() {
            circuitBreakerTestService.setFailing(true);
            return "Dependency is now DOWN";
        }

        @GetMapping("/status")
        public String status() {
            return circuitBreakerTestService.isFailing()
                    ? "DOWN"
                    : "UP";

        }

}
