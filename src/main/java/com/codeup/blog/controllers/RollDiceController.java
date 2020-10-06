package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    int dice;

    @GetMapping("/roll-dice/{n}")
    public String viewGuessDice(@PathVariable int n, Model model) {
        if(n != 0) {
            dice = (int) Math.floor(Math.random() * 6)+ 1;
            model.addAttribute("guess", n);
            model.addAttribute("dice", dice);
        }
        return "roll_dice";
    }
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll_dice";
    }
}