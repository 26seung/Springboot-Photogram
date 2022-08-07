package com.cos.photogramstart.domain.user;

// JPA - Java Persistance API (자바로 데이터를 영구적으로 저장(DB) 할 수 있는 API를 제공)

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.cos.photogramstart.domain.image.Image;
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

    @Column(unique = true, length = 20)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImanageUrl;   // 사진
    private String role;    // 권한

    //  나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지 않는다.
    //  User 를 SELECT 할 때 해당 User Id 로 등록된 image 를 모두 가져온다.
    //  Lazy = User 를 SELECT 할 때 해당 User Id 로 등록된 image 를 가져오지마 / 대신 getImages() 함수가 호출될 때 가져온다
    //  Eager = User 를 SELECT 할 때 해당 User Id 로 등록된 image 를 모두 Join 해서 가져온다.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images;     //  양방향 매핑

    private LocalDateTime createDate;
    @PrePersist // DB 에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
