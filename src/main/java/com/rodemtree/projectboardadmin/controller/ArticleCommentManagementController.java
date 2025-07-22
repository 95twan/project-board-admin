package com.rodemtree.projectboardadmin.controller;

import com.rodemtree.projectboardadmin.dto.ArticleCommentDto;
import com.rodemtree.projectboardadmin.dto.response.ArticleCommentResponse;
import com.rodemtree.projectboardadmin.service.ArticleCommentManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/management/article-comments")
@RequiredArgsConstructor
@Controller
public class ArticleCommentManagementController {

    private final ArticleCommentManagementService articleCommentManagementService;

    @GetMapping
    public String articleComments(Model model) {
        List<ArticleCommentDto> articleComments = articleCommentManagementService.getArticleComments();
        List<ArticleCommentResponse> response = articleComments.stream().map(ArticleCommentResponse::of).toList();

        model.addAttribute("comments", response);

        return "management/article-comments";
    }

    @ResponseBody
    @GetMapping("/{articleCommentId}")
    public ArticleCommentResponse articleComment(@PathVariable Long articleCommentId) {
        return ArticleCommentResponse.of(articleCommentManagementService.getArticleComment(articleCommentId));
    }

    @PostMapping("/{articleCommentId}")
    public String deleteArticleComment(@PathVariable Long articleCommentId) {
        articleCommentManagementService.deleteArticleComment(articleCommentId);

        return "redirect:/management/article-comments";
    }

}
