package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;     // Repository 는 EntityManager 를 구현해서 만들어져 있는 구현체


    @Transactional(readOnly = true)
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id ,u.username ,u.profileImanageUrl, ");
        sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id),1,0) subscribeState, ");
        sb.append("if((?=u.id),1,0) equalUserState ");
        sb.append("FROM `User` u inner join subscribe s ");
        sb.append("on u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?");        //      ; 세미콜론 들어오면 안됨

        // 1물음표 principalId
        // 2물음표 principalId
        // 3물음표 pageUserId

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1,principalId)
                .setParameter(2,principalId)
                .setParameter(3,pageUserId);

        // 쿼리 실행  (qlrm 라이브러리 필요 = DTO 에 매핑을 하기 위해서)
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDto = result.list(query, SubscribeDto.class);

        return subscribeDto;
    }


    @Transactional
    public void 구독하기(int fromUserId, int toUserId){
        try {
        subscribeRepository.mSubscribe(fromUserId,toUserId);
        }catch (Exception e){
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }
    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId){
        subscribeRepository.mUnSubscribe(fromUserId,toUserId);
    }

}
