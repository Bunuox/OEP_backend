package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TeachCreationRequest;
import org.springframework.stereotype.Service;

@Service
public interface TeachService {
    Boolean createTeach(TeachCreationRequest teachCreationRequest);
}
