package com.tobeto.java4a.pair4lms.services.abstracts;

import com.tobeto.java4a.pair4lms.services.dtos.requests.fines.AddFineRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.fines.AddFineResponse;

public interface FineService {
    AddFineResponse add(AddFineRequest request);
}
