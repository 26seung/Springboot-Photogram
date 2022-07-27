package com.cos.photogramstart.domain.user;

// JPA - Java Persistance API (자바로 데이터를 영구적으로 저장(DB) 할 수 있는 API를 제공)

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    private String username;
    private String password;

    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImanageUrl;   // 사진
    private String role;    // 권한

    private LocalDateTime createDate;

    @PrePersist // DB 에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
