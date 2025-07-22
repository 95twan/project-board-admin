package com.rodemtree.projectboardadmin.service;


import com.rodemtree.projectboardadmin.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserAccountManagementService {

    public List<UserAccountDto> getUserAccounts() {
        return List.of();
    }

    public UserAccountDto getUserAccount(String username) {
        return null;
    }

    public void deleteUserAccount(String username) {
    }
}
