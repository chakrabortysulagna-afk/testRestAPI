# Spring Boot Resilience4j Circuit Breaker Demo

A Spring Boot application demonstrating the **Circuit Breaker pattern using Resilience4j**.

The application contains a deliberately unreliable dependency that can be switched between **UP** and **DOWN**. 
This makes it easy to observe the Circuit Breaker moving through the following states:
CLOSED → OPEN → HALF_OPEN → CLOSED

**How the Project Works and How to Test**

This project demonstrates how a Resilience4j Circuit Breaker protects a Spring Boot service from an unreliable downstream dependency. 
The ClaimController exposes a GET /claims/{claimId} endpoint, which calls ClaimService. 
The getClaim() method in ClaimService is protected with Resilience4j’s @CircuitBreaker annotation and calls the deliberately unreliable CircuitBreakerTestService. 
When the dependency is available, the request succeeds normally; when it fails, the Circuit Breaker records the failure and returns a fallback response.
To test the Circuit Breaker, first start the application using ./mvnw spring-boot:run, then call POST /dependency/down to simulate a dependency failure. 
Call GET /claims/circuitBreakerTest/CLM-100123 at least five times to exceed the configured 50% failure-rate threshold. 
The Circuit Breaker then changes from CLOSED to OPEN, and subsequent requests fail fast without calling the dependency and return the fallback response. 
Wait for the configured 10-second open-state period, then call POST /dependency/up to simulate recovery. 
The Circuit Breaker moves to HALF_OPEN and allows a limited number of test requests. 
If those requests succeed, the Circuit Breaker transitions back to CLOSED, demonstrating the complete CLOSED → OPEN → HALF_OPEN → CLOSED lifecycle.

**Architecture**

                    GET /claims/{claimId}
                              |
                              v
                    +-------------------+
                    | ClaimController   |
                    +---------+---------+
                              |
                              v
                    +-------------------+
                    |   ClaimService    |
                    |                   |
                    | @CircuitBreaker   |
                    +---------+---------+
                              |
                              v
                    +-------------------+
                    |    Circuit       |
                    |    Breaker       |
                    +---------+---------+
                              |
                              v
                  +-------------------------+
                  |CircuitBreakerTestService|
                  +-------------------------+
                         |         |
                    SUCCESS       FAILURE
                    
**Complete State Transition**

                  Dependency DOWN
                        |
                        v
                    +--------+
                    | CLOSED |
                    +----+---+
                         |
                         | Failure rate >= 50%
                         v
                    +--------+
                    |  OPEN  |
                    +----+---+
                         |
                         | Wait 10 seconds
                         v
                  +-------------+
                  |  HALF_OPEN  |
                  +------+------+
                         |
              +----------+----------+
              |                     |
          SUCCESS                 FAILURE
              |                     |
              v                     v
          +-------+              +------+
          |CLOSED |              | OPEN |
          +-------+              +------+
          

**Resilience4j Configuration: The Circuit Breaker is configured in application.yml.**

resilience4j:
  circuitbreaker:
    instances:

      claimService:
        registerHealthIndicator: true

        slidingWindowType: COUNT_BASED

        slidingWindowSize: 10

        minimumNumberOfCalls: 5

        failureRateThreshold: 50

        waitDurationInOpenState: 10s

        permittedNumberOfCallsInHalfOpenState: 3

        slowCallRateThreshold: 50

        slowCallDurationThreshold: 2s
        
**Useful Commands**
Check dependency status:http://localhost:8080/dependency/status
Bring dependency DOWN: POST http://localhost:8080/dependency/down
Bring dependency UP:POST http://localhost:8080/dependency/up
Call Claims API:http://localhost:8080/claims/circuitBreakerTest/CLM-100123
