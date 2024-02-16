package com.example.servertuto.Controllers;


import com.example.servertuto.Dtos.TutoDto;
import com.example.servertuto.Services.TutoService;
import com.example.servertuto.models.TutoModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tutos")
@CrossOrigin(origins = "http://localhost:4200")
public class TutoCon {

    private final TutoService tutoService;

    public TutoCon(TutoService tutoService) {
        this.tutoService = tutoService;
    }

    @PostMapping
    public ResponseEntity<?> createTuto(@RequestBody  TutoDto tutoDto) {
        try {
            TutoModel tutoModel = TutoModel.builder()
                    .description(tutoDto.getDescription())
                    .title(tutoDto.getTitle())
                    .publishedStatus(tutoDto.getPublishedStatus())
                    .build();

            TutoModel createdTuto = tutoService.createTuto(tutoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTuto);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating tutorial: " + e.getMessage());

        }
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getTutoById(@PathVariable("id") Long id) {
        Optional<TutoModel> tutoModel = tutoService.getTutoById(id);
        return tutoModel.map(model -> ResponseEntity.status(HttpStatus.OK).body(model)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTutoById(@PathVariable("id") Long id) {
        Optional<TutoModel> tutoModel = tutoService.getTutoById(id);
        if (tutoModel.isPresent()) {
            tutoService.deleteTutoById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAllTutos() {
        try {
            tutoService.deleteAllTutos();
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "all tutos deleted");
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }catch (Exception e) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( responseMap);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllTutos(@RequestParam(name = "title", required = false) String title) {
        try {
            if (title == null) {
                return ResponseEntity.status(HttpStatus.OK).body(tutoService.getAllTutos());
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(tutoService.getAllTutosByTitle(title));
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting tutorials: " + e.getMessage());
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateTuto(@PathVariable("id") Long id, @RequestBody  TutoDto tutoDto) {
        Optional<TutoModel> tutoModel = tutoService.getTutoById(id);
        if (tutoModel.isPresent()) {
            TutoModel updatedTuto = tutoModel.get();
            updatedTuto.setTitle(tutoDto.getTitle());
            updatedTuto.setDescription(tutoDto.getDescription());
            updatedTuto.setPublishedStatus(tutoDto.getPublishedStatus());
            TutoModel savedTuto = tutoService.createTuto(updatedTuto);
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/published/{status}")
    public ResponseEntity<?> getAllTutosByPublishedStatus(@PathVariable("status") Boolean status) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tutoService.getAllTutosByPublishedStatus(status));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting tutorials: " + e.getMessage());
        }
    }
}


