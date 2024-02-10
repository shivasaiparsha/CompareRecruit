package com.tool.RecruitXpert.Controller;


import com.tool.RecruitXpert.ResumeUtility.ResumeUtilities;
import com.tool.RecruitXpert.Service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    ResumeUtilities resumeUtilities;

    // upload resume by specific user
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("image") MultipartFile file,@RequestParam("userid") Integer userid) throws IOException {
        try {
            String uploadResume = resumeService.saveResumeToDb(file, userid);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(uploadResume);
        }
        catch(IOException e)
        {
            log.error("resume size not in correct format");
            throw new IOException(e.getMessage());
        }
    }


    // view resume by id

    @GetMapping("/viewResume/{resumeid}")
    public ResponseEntity<?> viewImage(@PathVariable Integer  resumeid) throws Exception {

        ResponseEntity<?> resumedata = null;
        try {
            resumedata=resumeService.downloadResume(resumeid);

        }
        catch (Exception e)
        {
            log.error("resume with "+resumeid+" not found");
            throw new Exception(e.getMessage());
        }
        return resumedata;
    }

// delete resume by resume id
    @DeleteMapping("deleteResumeById{resumeid}")
    public ResponseEntity<String> deleteResume(@PathVariable Integer resumeid)
    {
        try {
            resumeService.deleteResumeByResumeId(resumeid);
        } catch (Exception e) {
            log.error("resumeId not found");
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>("file deleted sucessfully", HttpStatus.OK);
    }

    // delete resumes of user by userid
    @DeleteMapping("/deletebyUserId")
    public ResponseEntity<String> deleteUserById(@RequestParam("usreId") Integer userId) throws Exception {

        String message= resumeService.deleteUserByUserId(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // get All files of user
    @GetMapping(value = "/getAllFile", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getAllImages(@RequestParam Integer userid) throws Exception,IOException {


        List<InputStream> pdfStreams = resumeService.findAllResumes(userid); //  method to retrieve PDF streams
        InputStream combinedPdfStream = resumeUtilities.combinePdfStreams(pdfStreams); // it combine all the resumes of pdfstream
        InputStreamResource resource = new InputStreamResource(combinedPdfStream);// create the newinout stream

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


}
