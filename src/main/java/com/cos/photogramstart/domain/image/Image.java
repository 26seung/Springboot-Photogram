package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;              // tag 메시지
    private String postImageUrl;        //  사진을 전송 받아서 그 사진을 서버에 특정 폴더에 저장 -DB 에 그 저장된 경로를 INSERT

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;
    @PrePersist // DB 에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
