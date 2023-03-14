package com.mikechen.onework.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    @Column(name = "name")
	    private String name;
	    
	    @Column(name = "number")
	    private String number;
	    
	    @Column(name = "version")
	    private int version;
	    
	    @Column(name = "path")
	    private String path;
	    
	    @Column(name = "upload_date")
	    private LocalDateTime uploadDate;
	    
	    public Document() {}

		public Document(String name, String number, int version, String path, LocalDateTime uploadDate) {
			super();
			this.name = name;
			this.number = number;
			this.version = version;
			this.path = path;
			this.uploadDate = uploadDate;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public LocalDateTime getUploadDate() {
			return uploadDate;
		}

		public void setUploadDate(LocalDateTime uploadDate) {
			this.uploadDate = uploadDate;
		}
	    
	    
}
