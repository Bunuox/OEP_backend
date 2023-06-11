package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Take;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.TakeRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.TakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TakeServiceImpl implements TakeService {

    @Autowired
    private TakeRepository takeRepository;

    @Override
    public Boolean createTake(TakeCreationRequest takeCreationRequest) {
        Take take = Take.builder()
                .courseId(takeCreationRequest.getCourseId())
                .studentId(takeCreationRequest.getStudentId())
                .isActive(false)
                .build();
        try {
            takeRepository.save(take);
            log.info("Take successfully created.");
        } catch (Exception e) {
            log.info("Take could not created due to: " + e.getLocalizedMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Take[] findTake(TakeFindRequest takeFindRequest) {
        return takeRepository.findAllByStudentId(takeFindRequest.getStudentId());
    }

    @Override
    public Take[] findTakeByCourseId(TakeFindRequest takeFindRequest){
        return takeRepository.findAllByCourseId(takeFindRequest.getCourseId());
    }
}
