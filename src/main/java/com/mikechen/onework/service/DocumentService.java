package com.mikechen.onework.service;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mikechen.onework.dao.DocumentRepository;
import com.mikechen.onework.model.Document;

@Service
public class DocumentService {

	private DocumentRepository documentRepository;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	String uploadPath = "C:\\doc";

	@Autowired
	public DocumentService(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	public Document uploadDocument(MultipartFile file) throws IllegalStateException, IOException {

		// declare version as zero
		int version = 0;

		String originalFileName = file.getOriginalFilename();
		System.out.println("originalFileName " + originalFileName);

		Optional<Document> latestDocument = documentRepository.findLatestVersionByName(originalFileName);
		if (latestDocument.isPresent()) {
			Document document = latestDocument.get();
			version = document.getVersion() + 1;
			// if alreadly have this file version+1
		}
		LocalDateTime uploadTime = LocalDateTime.now();
		String number = generateNumber(originalFileName, uploadTime);
		// we store the file as new name as number we generate
		File dest = new File(uploadPath + "/" + number);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		// we store the originalName and we also store the path
		Document document = new Document(originalFileName, number, version, dest.getAbsolutePath(), uploadTime);
		documentRepository.save(document);
		file.transferTo(dest);

		return document;
	}

	public static String generateNumber(String fileName, LocalDateTime uploadTime) {
		String uniqueId = UUID.randomUUID().toString().substring(0, 8);
		String formattedTime = uploadTime.format(formatter);
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		return uniqueId + formattedTime + "." + extension;
	}

	public List<Document> getAllDocuments() {
		return documentRepository.findAll();
	}

	

}