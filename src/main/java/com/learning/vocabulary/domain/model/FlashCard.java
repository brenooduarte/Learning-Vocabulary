package com.learning.vocabulary.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class FlashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String front;
    private String back;
    private List<String> phrases;

    public FlashCard(String front, String back) {
        this.front = front;
        this.back = back;
    }

    public FlashCard(String front, String back, List<String> phrases) {
        this.front = front;
        this.back = back;
        this.phrases = phrases;
    }

}
