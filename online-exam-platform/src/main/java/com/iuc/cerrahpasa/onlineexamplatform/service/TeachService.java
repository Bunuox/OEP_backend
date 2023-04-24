package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Teach;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TeachCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TeachFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface TeachService {
    Boolean createTeach(TeachCreationRequest teachCreationRequest);

    Teach[] findTeach(TeachFindRequest teachFindRequest);

    Teach findTeachByCourseId(TeachFindRequest teachFindRequest);
}
