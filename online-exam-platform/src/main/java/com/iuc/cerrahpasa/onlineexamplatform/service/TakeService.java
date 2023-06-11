package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Take;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface TakeService {
    Boolean createTake(TakeCreationRequest takeCreationRequest);

    Take[] findTake(TakeFindRequest takeFindRequest);

    Take[] findTakeByCourseId(TakeFindRequest takeFindRequest);
}
