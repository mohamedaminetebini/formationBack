package com.example.servertuto.Services;

import com.example.servertuto.Repositories.TutoRepository;
import com.example.servertuto.models.TutoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TutoService {

    private final TutoRepository tutoRepository;

    public TutoModel createTuto(TutoModel tutoModel) {
       System.out.println(tutoModel.getTitle());
        System.out.println(tutoModel.getPublishedStatus());
        System.out.println(tutoModel.getDescription());
        return tutoRepository.save(tutoModel);
    }
    public Optional<TutoModel> getTutoById(Long id) {
        return Optional.ofNullable(tutoRepository.findById(id).orElse(null));
    }

    public void deleteTutoById(Long id) {
        tutoRepository.deleteById(id);
    }
    public void deleteAllTutos() {
        tutoRepository.deleteAll();
    }
    public List<TutoModel> getAllTutos() {
        return tutoRepository.findAll();
    }
    public List<TutoModel> getAllTutosByTitle(String title) {
        return tutoRepository.findByTitleContaining(title);
    }
    public Optional<TutoModel> getAllTutosByPublishedStatus(Boolean publishedStatus) {
        return tutoRepository.findByPublishedStatus(publishedStatus);
    }
}
