package com.learning.vocabulary.domain.repository;

import com.learning.vocabulary.domain.model.FlashCard;

import java.util.List;

public interface FlashCardRepository {

    FlashCard save(FlashCard flashCard);
    void remove(Long id);
    FlashCard findById(Long id);
    List<FlashCard> findAll();


}
