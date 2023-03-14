package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class Reply {
   
	@Id // primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 절략을 따라간다.
	private int id; // 오라클: 시퀀스 , Mysql: auto_increment
	
	@Column(nullable=false,length=200)
	private String content;
	
	// 연관관계
	@ManyToOne //여러개의 답변은 하나의 게시글에 있을수 있다.
	@JoinColumn(name="boardid")
	private Board board;
	
	@ManyToOne //하나의 유저는 여러글을 작성할수 있다. 
	@JoinColumn(name="userid")
	private User user;
	
	@CreationTimestamp // 시간 자동 완성
	private Timestamp cresteDate;
}
