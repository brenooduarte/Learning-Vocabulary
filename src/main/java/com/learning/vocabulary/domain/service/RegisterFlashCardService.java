package com.learning.vocabulary.domain.service;

import com.learning.vocabulary.domain.exception.EntidadeNaoEncontradaException;
import com.learning.vocabulary.domain.model.FlashCard;
import com.learning.vocabulary.domain.repository.FlashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterFlashCardService {

    @Autowired
    private FlashCardRepository flashCardRepository;

    public FlashCard save(FlashCard flashCard) {
        return flashCardRepository.save(flashCard);
    }

    public void remove(Long id) {
        try {
            flashCardRepository.remove(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe FlashCard com código %d", id));

        }
    }

}
