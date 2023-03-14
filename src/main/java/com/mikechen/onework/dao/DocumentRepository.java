package com.mikechen.onework.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mikechen.onework.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

	 @Query("SELECT d FROM Document d WHERE d.name = :name AND d.version = (SELECT MAX(d2.version) FROM Document d2 WHERE d2.name = :name)")
	 Optional<Document> findLatestVersionByName(@Param("name") String name);
	 
	 Document findByNumber(String number);
}