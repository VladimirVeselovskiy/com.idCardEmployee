package com.idCardEmployee.service;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;

import java.util.List;

public interface CardAccessService {
    List<CardAccess> getAllCardAccess();
    void saveCardAccess(CardAccess cardAccess);
    void updateCardAccess(CardAccess cardAccess);
    CardAccess getCardAccess(int id);
    void deleteCardAccess(int id);
    List<CardAccess> getAllByLevelAccess(LevelAccess levelAccess);
}
