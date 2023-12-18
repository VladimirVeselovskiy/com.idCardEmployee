package com.idCardEmployee.service;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.Department;
import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.exception.CardAccessException;
import com.idCardEmployee.exception.CardAccessNotFoundException;
import com.idCardEmployee.repository.CardAccessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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
    void shouldCardAccessException_WhenSaveCardAccess(){
        //given
        CardAccess cardAccess1 = new CardAccess(1, LevelAccess.ONE);
        Employee employee = new Employee(1,"Simons", Department.IT,
                LocalDate.of(1991,2,5), new CardAccess());
        cardAccess1.setEmployee(employee);

        //when
        assertThrows(CardAccessException.class, () -> cardAccessServiceImpl.saveCardAccess(cardAccess1));

        //then
        assertNotNull(cardAccess1.getEmployee());
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
    void shouldCardAccessNotFoundException_WhenUpdateCardAccess(){
        //given
        CardAccess cardAccess = new CardAccess();

        given(cardAccessRepository.findById(cardAccess.getId())).willReturn(Optional.empty());

        //when
        assertThrows(CardAccessNotFoundException.class, () -> cardAccessServiceImpl.updateCardAccess(cardAccess));

        //then
        verify(cardAccessRepository, times(1)).findById(cardAccess.getId());
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
    void shouldCardAccessException_WhenGetCardAccess(){
        //given
        int cardAccessId = 1;

        given(cardAccessRepository.findById(cardAccessId)).willReturn(Optional.empty());

        //when
        assertThrows(CardAccessNotFoundException.class, () -> cardAccessServiceImpl.getCardAccess(cardAccessId));

        //then
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
    void shouldCardAccessException_WhenDeleteCardAccess(){
        //given
        int cardAccessId = 1;

        //when
        doThrow(CardAccessNotFoundException.class).when(cardAccessRepository).deleteById(cardAccessId);
        try {
            cardAccessServiceImpl.deleteCardAccess(cardAccessId);
        }catch (CardAccessNotFoundException e){
           assertEquals("CardAccess is not found, id= " + cardAccessId, e.getMessage());
        }

        verify(cardAccessRepository, times(1)).deleteById(cardAccessId);
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