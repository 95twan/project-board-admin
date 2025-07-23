package com.rodemtree.projectboardadmin.dto.response;

import com.rodemtree.projectboardadmin.dto.ArticleCommentDto;
import com.rodemtree.projectboardadmin.dto.UserAccountDto;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        UserAccountDto userAccount,
        String content,
        LocalDateTime createdAt
) {
        public static ArticleCommentResponse of(Long id, UserAccountDto userAccount, String content, LocalDateTime createdAt) {
                return new ArticleCommentResponse(id, userAccount, content, createdAt);
        }

        public static ArticleCommentResponse of(ArticleCommentDto dto) {
                return ArticleCommentResponse.of(dto.id(), dto.userAccount(), dto.content(), dto.createdAt());
        }
}
