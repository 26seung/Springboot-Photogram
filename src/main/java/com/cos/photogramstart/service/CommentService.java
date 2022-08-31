package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    @Transactional
    public Comment 댓글쓰기(String content, int imageId, int userId){

        // 객체를 만들 때 id 값만 담아서 insert 할 수 있다.
        Image image = new Image();
        image.setId(imageId);

        User user = new User();
        user.setId(userId);


        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(user);

        commentRepository.mSave(content, imageId, userId);
        return null;
    }
    @Transactional
    public void 댓글삭제(){

    }
}
