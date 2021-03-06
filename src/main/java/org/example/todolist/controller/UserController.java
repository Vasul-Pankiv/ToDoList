package org.example.todolist.controller;

import org.example.todolist.domain.Role;
import org.example.todolist.domain.User;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String userProfile(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user",user);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String userProfileEdit(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user",user);
        return "profileEdit";
    }

    @PostMapping("/profile/edit")
    public String updateUserProfile(
            @AuthenticationPrincipal User CurrentUser,
            @Valid User user,
            BindingResult bindingResult,
            Model model

    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("user",user);
            return "profileEdit";
        }else {
            userService.updateProfile(CurrentUser,user.getUsername(),user.getPassword(), user.getEmail());
            return "redirect:/user/profile";
        }
    }

}
