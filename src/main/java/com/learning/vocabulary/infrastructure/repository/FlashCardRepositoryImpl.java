package com.learning.vocabulary.infrastructure.repository;

import com.learning.vocabulary.domain.model.FlashCard;
import com.learning.vocabulary.domain.repository.FlashCardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlashCardRepositoryImpl implements FlashCardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public FlashCard save(FlashCard flashCard) {
        return entityManager.merge(flashCard);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        FlashCard flashCard = findById(id);

        if (flashCard == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(flashCard);
    }

    @Override
    public FlashCard findById(Long id) {
        return entityManager.find(FlashCard.class, id);
    }

    @Override
    public List<FlashCard> findAll() {
        return entityManager.createQuery("from FlashCard", FlashCard.class)
                .getResultList();
    }

}
