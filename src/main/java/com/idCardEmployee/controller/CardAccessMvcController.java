package com.idCardEmployee.controller;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.service.CardAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/id-card-employee")
public class CardAccessMvcController {

    @Autowired
    private CardAccessService cardAccessService;

    @RequestMapping("/card-access")
    public String showAllCardAccess(Model model){
        List<CardAccess> cardAccessList = cardAccessService.getAllCardAccess();
        model.addAttribute("allCardAccess", cardAccessList);

        return "all_cardAccess";
    }

    @RequestMapping("/card-access/addNewCardAccess")
    public String addNewCardAccess(Model model){
        CardAccess cardAccess = new CardAccess();
        model.addAttribute("cardAccess", cardAccess);

        return "add_cardAccess";
    }

    @RequestMapping("/card-access/saveCardAccess")
    public String saveCardAccess(@ModelAttribute("cardAccess") CardAccess cardAccess){
        cardAccessService.saveCardAccess(cardAccess);

        return "redirect:/id-card-employee/card-access";
    }

    @RequestMapping("/card-access/update-cardAccess")
    public String getAndUpdateCardAccess(@RequestParam("cardId") int id, Model model){
        CardAccess cardAccess = cardAccessService.getCardAccess(id);
        model.addAttribute("cardAccess", cardAccess);

        return "update_cardAccess";
    }

    @RequestMapping("/card-access/updateCardAccess")
    public String updateCardAccess(@ModelAttribute("cardAccess") CardAccess cardAccess){
        cardAccessService.updateCardAccess(cardAccess);

        return "redirect:/id-card-employee/card-access";
    }

    @RequestMapping("/card-access/deleteCardAccess")
    public String deleteCardAccess(@RequestParam("cardId") int id){
        cardAccessService.deleteCardAccess(id);

        return "redirect:/id-card-employee/card-access";
    }

    @RequestMapping("/card-access/levelAccess")
    public String searchCardAccessByLevelAccess(@RequestParam("levelAccess") LevelAccess levelAccess, Model model) {
        List<CardAccess> cardAccessListByLevelAccess = cardAccessService.getAllByLevelAccess(levelAccess);
        model.addAttribute("cardAccess", cardAccessListByLevelAccess);

        return "cardAccess_by_levelAccess";
    }
}
