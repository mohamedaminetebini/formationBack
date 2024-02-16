package com.example.servertuto.Repositories;

import com.example.servertuto.models.TutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutoRepository extends JpaRepository<TutoModel, Long> {
    List<TutoModel> findByTitleContaining(String title);
    Optional<TutoModel> findByPublishedStatus(Boolean publishedStatus);
}
