package com.tool.RecruitXpert.Transformer;

import com.tool.RecruitXpert.Entities.ResumeEntity;
import com.tool.RecruitXpert.Entities.User;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Builder
public class ResumeBuilderTransformer {

    public static ResumeEntity buildResumeObject(MultipartFile file, User user) throws IOException {

        ResumeEntity resume = ResumeEntity.builder()
                .resumeName(file.getOriginalFilename())
                .docType(file.getContentType())
                .user(user)
                .comment("uploaded")
                .resume(file.getBytes()).build();
        return resume;
    }
}
