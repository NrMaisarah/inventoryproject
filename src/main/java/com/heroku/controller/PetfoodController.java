package com.heroku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heroku.java.models.Petfood;
import com.heroku.services.PetfoodService; //untuk DAO

@Controller
@RequestMapping("/petfood")
public class PetfoodController {

    @Autowired
    private PetfoodService petfoodService;

    @GetMapping("/create")
    public String showCreateForm(@RequestParam("staffID") int staffID, Model model) {
        model.addAttribute("staffID", staffID);
        return "createPetFood";
    }

    @PostMapping("/create")
    public String createPetFood(@ModelAttribute Petfood petfood, RedirectAttributes redirectAttributes) {
        try {
            petfoodService.createPetFood(petfood);
            redirectAttributes.addFlashAttribute("message", "Pet food created successfully!");
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid input: Please enter valid numbers for price, weight, quantity, and reorder point.");
            return "redirect:/petfood/create?staffID=" + petfood.getStaffID();
        }
        
        return "redirect:/petfood/list?staffID=" + petfood.getStaffID();
    }

    @GetMapping("/list")
    public String listPetFood(@RequestParam("staffID") int staffID, Model model) {
        model.addAttribute("petfoods", petfoodService.getAllPetFood());
        model.addAttribute("staffID", staffID);
        return "listPetFood";
    }
}