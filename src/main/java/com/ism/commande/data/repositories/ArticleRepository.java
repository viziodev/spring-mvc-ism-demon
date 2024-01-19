package com.ism.commande.data.repositories;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
