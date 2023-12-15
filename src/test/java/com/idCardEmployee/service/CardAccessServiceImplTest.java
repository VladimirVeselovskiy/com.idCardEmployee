package com.idCardEmployee.service;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.repository.CardAccessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CardAccessServiceImplTest {

    @Mock
    private CardAccessRepository cardAccessRepository;

    @InjectMocks
    private CardAccessServiceImpl cardAccessServiceImpl;

    @Test
    void shouldGetAllCardAccess() {
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);
        CardAccess cardAccess2 = new CardAccess(1, LevelAccess.TWO);

        given(cardAccessRepository.findAll()).willReturn(List.of(cardAccess1, cardAccess2));

        //when
        List<CardAccess> cardAccessList = cardAccessServiceImpl.getAllCardAccess();

        //then
        assertThat(cardAccessList).isNotNull();
        assertThat(cardAccessList.size()).isEqualTo(2);
        verify(cardAccessRepository, times(1)).findAll();
    }
    @Test
    void shouldSaveCardAccess() {
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);

        given(cardAccessRepository.save(cardAccess1)).willReturn(cardAccess1);

        //when
        cardAccessServiceImpl.saveCardAccess(cardAccess1);

        //then
        verify(cardAccessRepository, times(1)).save(cardAccess1);
    }

    @Test
    void shouldUpdateCardAccess() {
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);

        given(cardAccessRepository.findById(cardAccess1.getId())).willReturn(Optional.of(cardAccess1));
        cardAccess1.setLevelAccess(LevelAccess.TWO);

        //when
        cardAccessServiceImpl.updateCardAccess(cardAccess1);

        //then
        assertNotNull(cardAccess1);
        verify(cardAccessRepository, times(1)).findById(cardAccess1.getId());
        verify(cardAccessRepository, times(1)).save(cardAccess1);
    }

    @Test
    void shouldGetCardAccess() {
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);

        given(cardAccessRepository.findById(cardAccess1.getId())).willReturn(Optional.of(cardAccess1));

        //when
        int cardAccessId = cardAccessServiceImpl.getCardAccess(cardAccess1.getId()).getId();

        //then
        assertThat(cardAccessId).isNotNull();
        verify(cardAccessRepository, times(1)).findById(cardAccessId);
    }

    @Test
    void shouldDeleteCardAccess() {
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);

        willDoNothing().given(cardAccessRepository).deleteById(cardAccess1.getId());

        //when
        cardAccessServiceImpl.deleteCardAccess(cardAccess1.getId());

        //then
        assertThat(cardAccess1.getId()).isNotNull();
        verify(cardAccessRepository, times(1)).deleteById(cardAccess1.getId());
    }

    @Test
    void shouldGetAllByLevelAccess() {
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);
        CardAccess cardAccess2 = new CardAccess(2, LevelAccess.ONE);

        given(cardAccessRepository.findAllByLevelAccess(cardAccess1.getLevelAccess()))
                .willReturn(List.of(cardAccess1, cardAccess2));

        //when
        cardAccessServiceImpl.getAllByLevelAccess(LevelAccess.ONE);

        //then
        verify(cardAccessRepository, times(1)).findAllByLevelAccess(cardAccess1.getLevelAccess());
    }
}