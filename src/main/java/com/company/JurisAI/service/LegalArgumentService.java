package com.company.JurisAI.service;


import com.company.JurisAI.dtos.LegalArgumentRequestDto;
import com.company.JurisAI.dtos.LegalArgumentResponseDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LegalArgumentService {

    public LegalArgumentResponseDto generateArguments(LegalArgumentRequestDto requestDto){
        log.info("Generating legal arguments for issue: {}", requestDto.getIssue());

        List<String> arguments = new ArrayList<>();

        // sample logic
        // TODO replace this logic with AI or NLP integration later
        if (requestDto.getIssue() != null && requestDto.getIssue().toLowerCase().contains("breach of contract")) {
            arguments.add("There was a legally binding agreement between the parties.");
            arguments.add("The defendant failed to fulfill their contractual obligations.");
            arguments.add("The plaintiff suffered damages as a result of the breach.");
        }

        if (requestDto.getJurisdiction() != null) {
            arguments.add("According to legal precedents in the jurisdiction of " + requestDto.getJurisdiction() + ", the ruling favors the plaintiff in similar cases.");
        }

        if (requestDto.getFacts() != null && requestDto.getFacts().toLowerCase().contains("damages")) {
            arguments.add("The facts establish clear evidence of monetary loss, which strengthens the claim for damages.");
        }

        if (arguments.isEmpty()) {
            arguments.add("Insufficient data to generate specific legal arguments. Please provide more details.");
        }

        LegalArgumentResponseDto response = new LegalArgumentResponseDto();
        response.setArguments(arguments);
        return response;
    }
}
