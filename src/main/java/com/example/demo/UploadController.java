package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private AIService aiService;
    private PrescriptionRepository repo;

   @PostMapping("/upload")
public String uploadFile(@RequestParam("file") MultipartFile file) {

    try {

        // ❗ Error Handling 1: Empty file
        if (file.isEmpty()) {
            return "Please retake the image clearly!";
        }

        byte[] bytes = file.getBytes();
        String hash = ImageHashUtil.generateHash(bytes);

        // 🔍 DB check
        var existing = repo.findByHash(hash);
        if (existing.isPresent()) {
            return "From DB: " + existing.get().getResult();
        }

        // 🤖 AI call (or dummy)
        String result = aiService.callAI("exercise recommendation");

        // 💾 Save to DB
        Prescription p = new Prescription();
        p.setHash(hash);
        p.setResult(result);
        repo.save(p);

        return "New Result: " + result;

    } catch (Exception e) {

        // ❗ Error Handling 2: AI or processing failure
        return "Unable to process image. Please try again.";
    }
}