package com.idCardEmployee.controller;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.service.CardAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardAccessRestController {

    @Autowired
    private CardAccessService cardAccessService;

    @GetMapping("/cards")
    public List<CardAccess> showAllCardAccess(){
        return cardAccessService.getAllCardAccess();
    }

    @GetMapping("/cards/{id}")
    public CardAccess showCardAccessById(@PathVariable int id){
        return cardAccessService.getCardAccess(id);
    }

    @GetMapping("/cards/levelAccess/{levelAccess}")
    public List<CardAccess> showAllByLevelAccess(@PathVariable LevelAccess levelAccess){
        return cardAccessService.getAllByLevelAccess(levelAccess);
    }

    @PostMapping("/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CardAccess addNewCardAccess(@RequestBody CardAccess cardAccess){
        cardAccessService.saveCardAccess(cardAccess);
        return cardAccess;
    }

    @PutMapping("/cards/{id}")
    public CardAccess updateCardAccess(@RequestBody CardAccess cardAccess){
        cardAccessService.updateCardAccess(cardAccess);
        return cardAccess;
    }

    @DeleteMapping("/cards/{id}")
    public String deleteCardAccess(@PathVariable int id){
        cardAccessService.deleteCardAccess(id);
        return "Карта доступа с id: " + id + " удалена";
    }
}
