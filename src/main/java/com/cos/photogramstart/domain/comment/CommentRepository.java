package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    // Comment 타입으로 입력이 되지 않음 int 형식을 사용해야 하나 그러면 Comment 의 id 값을 읽을수는 없다
//    @Modifying
//    @Query(value = "INSERT INTO comment(content, imageId, userId, createDate) VALUES (:content, :imageId, :userId, now())", nativeQuery = true)
//    Comment mSave(String content, int imageId, int userId);

}
