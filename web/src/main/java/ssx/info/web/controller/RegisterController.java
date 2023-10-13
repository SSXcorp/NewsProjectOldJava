package ssx.info.web.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ssx.info.web.exception.NullEntityReferenceException;
//import ssx.info.web.model.Role;
import ssx.info.web.model.User;
import ssx.info.web.service.UserService;

public class RegisterController {

    private final UserService userService;


    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new NullEntityReferenceException("Error occurred, check your input before creating user");
        }
        user.setPassword(user.getPassword());
//        user.setRole(Role.valueOf("ADMIN"));
        user.setRole("ADMIN");
        User newUser = userService.create(user);
        return "redirect:/todos/all/users/" + newUser.getId();
    }
}
