package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
//ORM->Java(다른언어)object ->테이블로 매핑해주는 기술
@Entity // User 클래스가 읽어서 Mysql에 테이블이 생성이 된다. 
@DynamicInsert //insert시에 null인 필드를 제외 해준다.
public class User {
  
	@Id // primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 절략을 따라간다.
	private int id; // 오라클: 시퀀스 , Mysql: auto_increment
	
	@Column(nullable=false,length=100)
	private String username;
	
	@Column(nullable=false,length=100)//123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable=false,length=30)
	private String email; 
	
	//@ColumnDefault("'user'") // 문자인 것을 알려주기 위해 ''사용
	//DB는 RoleType 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 사용한다. => 도메인 (범위)를 정해준다. //user,admin ,manager
	
	private String oauth; // kakao, googel
	
	@CreationTimestamp // 시간이 자동으로 입력 (현재 )
    private Timestamp createDate;
}
