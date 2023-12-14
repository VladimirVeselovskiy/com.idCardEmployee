package com.idCardEmployee.service;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.exception.CardAccessNotFoundException;
import com.idCardEmployee.repository.CardAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardAccessServiceImpl implements CardAccessService{

    @Autowired
    private CardAccessRepository cardAccessRepository;
    @Override
    public List<CardAccess> getAllCardAccess() {
        return cardAccessRepository.findAll();
    }

    /** метод должен сохраняет только карту с уровнем доступа, без сотрудника */
    @Override
    public void saveCardAccess(CardAccess cardAccess) {
        //TODO надо добавить выброс исключения если пытаемся сохранить и сотрудника
        cardAccessRepository.save(cardAccess);
    }
    /** метод должен обновляет только карту с уровнем доступа, без сотрудника */
    @Override
    public void updateCardAccess(CardAccess cardAccess) {
        Optional<CardAccess> updateCardAccess = cardAccessRepository.findById(cardAccess.getId());
        if (updateCardAccess.isEmpty()){
            throw new CardAccessNotFoundException(cardAccess.getId());
        }
        else {
            //TODO надо добавить выброс исключения если пытаемся обновить и сотрудника
            CardAccess card = updateCardAccess.get();
            card.setLevelAccess(cardAccess.getLevelAccess());
            cardAccessRepository.save(card);
        }
    }
    @Override
    public CardAccess getCardAccess(int id) {
        Optional<CardAccess> getCardAccess = cardAccessRepository.findById(id);
        return getCardAccess.orElseThrow(() -> new CardAccessNotFoundException(id));
    }
    @Override
    public void deleteCardAccess(int id) {
        try {
            cardAccessRepository.deleteById(id);
        }catch (Exception e){
            throw new CardAccessNotFoundException(id);
        }
    }
    @Override
    public List<CardAccess> getAllByLevelAccess(LevelAccess levelAccess) {
        return cardAccessRepository.findAllByLevelAccess(levelAccess);
    }
}
