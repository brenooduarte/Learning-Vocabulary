package com.learning.vocabulary.api.controller;

import com.learning.vocabulary.domain.exception.EntidadeNaoEncontradaException;
import com.learning.vocabulary.domain.model.FlashCard;
import com.learning.vocabulary.domain.repository.FlashCardRepository;
import com.learning.vocabulary.domain.service.RegisterFlashCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashcards")
public class RegisterFlashCardController {

    @Autowired
    private RegisterFlashCardService registerFlashCard;

    @Autowired
    private FlashCardRepository flashCardRepository;

    @GetMapping("/{id}")
    public ResponseEntity<FlashCard> findById(@PathVariable Long id) {
        FlashCard flashCard = flashCardRepository.findById(id);

        if (flashCard == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(flashCard);
    }

    @GetMapping
    public ResponseEntity<List<FlashCard>> findAll() {
        return ResponseEntity.ok(flashCardRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<FlashCard> create(@RequestBody FlashCard flashCard) {
        flashCard = registerFlashCard.save(flashCard);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flashCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FlashCard flashCard) {
        try {
            FlashCard currenrFlashCard = flashCardRepository.findById(id);

            if (currenrFlashCard != null) {
                BeanUtils.copyProperties(flashCard, currenrFlashCard, "id");

                registerFlashCard.save(currenrFlashCard);
                return ResponseEntity.ok(currenrFlashCard);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            registerFlashCard.remove(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }
    }


}
