package com.mepros.springbootsecurity.controller;

import com.mepros.springbootsecurity.entities.Role;
import com.mepros.springbootsecurity.entities.User;
import com.mepros.springbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String printHomePage(ModelMap model) {
        return "redirect:/login";
    }

    @GetMapping(value = "/admin")
    public String printUsersPage(ModelMap model) {
        List<User> users = userService.getAllUsers();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User newUser = new User();
        User editUser = new User();
        model.addAttribute("admin", user);
        model.addAttribute("usersList", users);
        model.addAttribute("newUser", newUser);
        model.addAttribute("editUser", editUser);
        model.addAttribute("rolesSet", userService.getAllRoles());
        return "index";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user")User user, String[] myRoles) {
        Set<Role> setRoles = new HashSet<>();

        for (String roleName : myRoles) {
            setRoles.add(userService.getRoleByName(roleName));
        }

        user.setRoles(setRoles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user, String[] myRoles) {
        Set<Role> setRoles = new HashSet<>();

        for (String roleName : myRoles) {
            setRoles.add(userService.getRoleByName(roleName));
        }

        user.setRoles(setRoles);

        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String userDelete(@ModelAttribute("user") User user) {
        userService.removeUserById(user.getId());
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String printUserPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "userpage";
    }
}
