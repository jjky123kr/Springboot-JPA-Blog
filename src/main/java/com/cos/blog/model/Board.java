package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.xml.ws.BindingType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false,length=100)
	private String title;
	
	@Lob
	private String content; //썸머노트 라이브러리 <html>태그가 섞어서 디지인
	
	private int count; //조회수
	
	// DB 오브젝트를 저장을 할수 없고지만, FK ,자바를 오브젝트를 저장된다.
    //자바가 DB 방식으로 따라가서, FK키로 저장을 한다. 
	// ORM은 @JoinColumn(name="userid")
	@ManyToOne(fetch=FetchType.EAGER)// Many=Board , one=User 
	@JoinColumn(name="userid")
	private User user; // 작성자 
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다 (난 FK가 아니예요) DB에 컬럼을 만들지 마세요.
	@JsonIgnoreProperties({"board", "user"})
	@OrderBy("id desc")
	private List<Reply> replys;
	                              
	@CreationTimestamp // 자동으로 현재시간 
	private Timestamp createDate;
	
}
