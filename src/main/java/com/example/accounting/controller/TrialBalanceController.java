package com.example.accounting.controller;

import com.example.accounting.service.TrialBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/trial-balance")
@RequiredArgsConstructor
public class TrialBalanceController {

    private final TrialBalanceService trialBalanceService;

    @GetMapping
    public Map<String, Object> getTrialBalance(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return trialBalanceService.calculate(from, to);
    }
}

