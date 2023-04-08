package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeCreationRequest;
import org.springframework.stereotype.Service;

@Service
public interface TakeService {
    Boolean createTake(TakeCreationRequest takeCreationRequest);
}
