package com.company.JurisAI.service;


import com.company.JurisAI.dtos.SummarizeRequestDto;
import com.company.JurisAI.dtos.SummarizeResponseDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummarizeService {


    public SummarizeResponseDto summarizeText(SummarizeRequestDto requestDto){
        String inputText = requestDto.getText();

        if(inputText==null || inputText.trim().isEmpty()){
            return new SummarizeResponseDto("Input text is empty.Please provide valid content.");
        }

        // simple-sentence=based  summarization split into sentence
        String[] sentences = inputText.split("(?<=[.!?])\\s+");

        // Sort sentences by length (descending)
        List<String> topSentences = Arrays.stream(sentences)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(Math.max(1, sentences.length / 3)) // Take top 1/3 sentences
                .collect(Collectors.toList());

        // Join top sentences into a summary
        String summary = String.join(" ",topSentences);
        return new SummarizeResponseDto(summary.trim());
    }
}
