package com.rodemtree.projectboardadmin.controller;

import com.rodemtree.projectboardadmin.dto.UserAccountDto;
import com.rodemtree.projectboardadmin.dto.response.UserAccountResponse;
import com.rodemtree.projectboardadmin.service.UserAccountManagementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/management/user-accounts")
@RequiredArgsConstructor
@Controller
public class UserAccountManagementController {

    private final UserAccountManagementService userAccountManagementService;

    @GetMapping
    public String userAccounts(Model model) {
        List<UserAccountDto> userAccounts = userAccountManagementService.getUserAccounts();
        List<UserAccountResponse> response = userAccounts.stream()
                .map(UserAccountResponse::from)
                .toList();

        model.addAttribute("userAccounts", response);

        return "management/user-accounts";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserAccountResponse userAccount(@PathVariable String userId) {
        return UserAccountResponse.from(userAccountManagementService.getUserAccount(userId));
    }

    @PostMapping("/{userId}")
    public String deleteUserAccount(@PathVariable String userId) {
        userAccountManagementService.deleteUserAccount(userId);

        return "redirect:/management/user-accounts";
    }

}
