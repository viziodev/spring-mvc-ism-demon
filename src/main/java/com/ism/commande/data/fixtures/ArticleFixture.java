package com.ism.commande.data.fixtures;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Categorie;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.data.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class ArticleFixture  implements CommandLineRunner {
    private final ArticleRepository articleRepository;
    private final CategorieRepository categorieRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = new ArrayList<Article>();
        for (int i = 1; i <=4 ; i++) {
            Categorie categorie=categorieRepository.findById(Long.valueOf(i)).get();
            for (int j = 1; j <5; j++) {
                articles.add( new Article("article"+i+j ,null,1000,10000,"",true,10,categorie));
            }
        }
        articleRepository.saveAllAndFlush(articles);
    }
}
