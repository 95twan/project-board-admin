package com.rodemtree.projectboardadmin.controller;

import com.rodemtree.projectboardadmin.dto.ArticleDto;
import com.rodemtree.projectboardadmin.dto.response.ArticleResponse;
import com.rodemtree.projectboardadmin.service.ArticleManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/management/articles")
@Controller
public class ArticleManagementController {

    private final ArticleManagementService articleManagementService;

    @GetMapping
    public String articles(Model model) {
        List<ArticleDto> articles = articleManagementService.getArticles();
        List<ArticleResponse> articlesResponse = articles.stream().map(ArticleResponse::withoutContent).toList();
        model.addAttribute("articles", articlesResponse);

        return "management/articles";
    }

    @ResponseBody
    @GetMapping("/{articleId}")
    public ArticleResponse article(@PathVariable Long articleId) {
        return ArticleResponse.withContent(articleManagementService.getArticle(articleId));
    }

    @PostMapping("/{articleId}")
    public String deleteArticle(@PathVariable Long articleId) {
        articleManagementService.deleteArticle(articleId);

        return "redirect:/management/articles";
    }

}
