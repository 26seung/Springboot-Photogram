package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(             // 1개만 유니크를 사용하면 @Column 에 넣으면 되지만 2개이상의 복합적인 설정을 위해서 해당 적용
        name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"imageId","userId"}
                )
        }
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image;
    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;          //  1번 유저가 1번 이미지들 동일하게 중복으로 좋아할 수는 없으니 @UniqueConstraint 를 설정

    private LocalDateTime createDate;
    @PrePersist // DB 에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
