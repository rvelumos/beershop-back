package nl.ronald.beershop.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import nl.ronald.beershop.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/uploadfile")
public class DocumentController {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void addDocument(@RequestParam(value = "documents") MultipartFile[] multipartFiles)
            throws NoSuchAlgorithmException, IOException {
        documentService.addDocuments(multipartFiles);
    }
}