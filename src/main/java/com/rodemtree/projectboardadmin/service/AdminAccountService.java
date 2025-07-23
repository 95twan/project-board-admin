package com.rodemtree.projectboardadmin.service;

import com.rodemtree.projectboardadmin.domain.AdminAccount;
import com.rodemtree.projectboardadmin.domain.constant.RoleType;
import com.rodemtree.projectboardadmin.dto.AdminAccountDto;
import com.rodemtree.projectboardadmin.repository.AdminAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminAccountService {

    private final AdminAccountRepository adminAccountRepository;

    @Transactional(readOnly = true)
    public Optional<AdminAccountDto> searchUser(String username) {

        return adminAccountRepository.findById(username)
                .map(AdminAccountDto::from);
    }

    public AdminAccountDto saveUser(
            String username,
            String password,
            Set<RoleType> roleTypes,
            String email,
            String nickname,
            String memo
    ) {
        AdminAccount adminAccount = AdminAccount.of(username, password, roleTypes, email, nickname, memo, username);
        AdminAccount savedAdminAccount = adminAccountRepository.save(adminAccount);

        return AdminAccountDto.from(savedAdminAccount);
    }

    @Transactional(readOnly = true)
    public List<AdminAccountDto> users() {

        return adminAccountRepository.findAll().stream()
                .map(AdminAccountDto::from)
                .toList();
    }

    public void deleteUser(String username) {
        adminAccountRepository.deleteById(username);
    }

}
