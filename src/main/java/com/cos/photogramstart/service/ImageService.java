package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    @Transactional(readOnly = true)     //  영속성 컨텍스트 변경 감지를 해서, 더치 체킹, Flush(반영) 을 하지 않음,  트랜잭션을 사용하는 이유는 세션을 컨트롤러 단까지 끌고 오기 위해서
    public Page<Image> 이미지스토리(int principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        // images 좋아요 상태담기
        images.forEach((image)->{

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like)->{
                if(like.getUser().getId() == principalId){      //  해당 이미지에 좋아요 한 사람들을 찾아서 현재 로그인 사람이 좋아요 한것인지 비교
                    image.setLikeState(true);
                }
            });
        });

        return images;
    }

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName =uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        System.out.println("이미지 파일 이름 : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신 I/O  -> 예외가 발생할 수 있다.
        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        imageRepository.save(image);

//        System.out.println("imageEntity : " + imageEntity);
    }
}
