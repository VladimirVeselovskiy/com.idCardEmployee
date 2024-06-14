package com.idCardEmployee.controller.mvc;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.service.CardAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/id-card-employee")
public class CardAccessMvcController {

    @Autowired
    private CardAccessService cardAccessService;

    @GetMapping("/card-access")
    public String showAllCardAccess(Model model){
        List<CardAccess> cardAccessList = cardAccessService.getAllCardAccess();
        model.addAttribute("allCardAccess", cardAccessList);

        return "card_access/all_cardAccess";
    }

    @GetMapping("/card-access/addNewCardAccess")
    public String addNewCardAccess(Model model){
        CardAccess cardAccess = new CardAccess();
        model.addAttribute("cardAccess", cardAccess);

        return "card_access/add_cardAccess";
    }

    @PostMapping("/card-access/saveCardAccess")
    public String saveCardAccess(@ModelAttribute("cardAccess") CardAccess cardAccess){
        cardAccessService.saveCardAccess(cardAccess);

        return "redirect:/id-card-employee/card-access";
    }

    @GetMapping("/card-access/updateCardAccess/{id}")
    public String getAndUpdateCardAccess(@PathVariable int id, Model model){
        CardAccess cardAccess = cardAccessService.getCardAccess(id);
        model.addAttribute("cardAccess", cardAccess);

        return "card_access/update_cardAccess";
    }

    @PostMapping("/card-access/updateCardAccess")
    public String updateCardAccess(@ModelAttribute("cardAccess") CardAccess cardAccess){
        cardAccessService.updateCardAccess(cardAccess);

        return "redirect:/id-card-employee/card-access";
    }

    @GetMapping("/card-access/deleteCardAccess/{id}")
    public String deleteCardAccess(@PathVariable int id){
        cardAccessService.deleteCardAccess(id);

        return "redirect:/id-card-employee/card-access";
    }

    @GetMapping("/card-access/levelAccess")
    public String searchCardAccessByLevelAccess(@RequestParam("levelAccess") LevelAccess levelAccess, Model model) {
        List<CardAccess> cardAccessListByLevelAccess = cardAccessService.getAllByLevelAccess(levelAccess);
        model.addAttribute("cardAccess", cardAccessListByLevelAccess);

        return "card_access/cardAccess_by_levelAccess";
    }
}
