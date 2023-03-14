package com.mikechen.onework.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mikechen.onework.dao.DocumentRepository;
import com.mikechen.onework.model.Document;
import com.mikechen.onework.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

	

	@Autowired
	DocumentService documentService;
	@Autowired
	DocumentRepository documentRepository;

	@PostMapping("/upload")
	public HashMap<String ,Object> uploadDocument(@RequestParam("file") MultipartFile file)  {
		HashMap<String ,Object> map =new HashMap<>();
		
		try {
			Document doc = documentService.uploadDocument(file);
			
			map.put("status", 200);
	        map.put("message", "上傳檔案成功");
	        map.put("document", doc);
	        return map;
		}catch(Exception e){
			map.put("status", 400);
        	map.put("message", "上傳檔案失敗");
        	map.put("document", new Document());
        	return map;
		}
	  
	}

	 @GetMapping
	 public List<Document> getAllDocuments() {
		return documentService.getAllDocuments();
	 }
	
	 @GetMapping("/download/{number}")
	 public HashMap<String ,Object>downloadDocument(@PathVariable String number) throws IOException {
	        
			HashMap<String ,Object> map =new HashMap<>();
			Document document = documentRepository.findByNumber(number);
		
	        if (document == null) {
	        	map.put("status", 400);
	        	map.put("message", "該文件不存在");
	        	map.put("base64", "該文件不存在");
	        	return map;
	        }
	    	System.out.println(document.toString());
	        Path path = Paths.get(document.getPath());
	      
	        String base64String = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
	        map.put("status", 200);
        	map.put("message", "該文件存在");
        	map.put("base64", base64String);
			return map;
	       
	    }
	
	
	
}
