package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TB_ARTICLE")
public class Article {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long internalId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = true)
    private User user;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private Boolean deleted;

    private Long hitCount;
    
    private Date createDate;

	
}
