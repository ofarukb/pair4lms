package com.tobeto.java4a.pair4lms.services.concretes;

import com.tobeto.java4a.pair4lms.entities.Fine;
import com.tobeto.java4a.pair4lms.repositories.FineRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.FineService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.fines.AddFineRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.fines.AddFineResponse;
import com.tobeto.java4a.pair4lms.services.mappers.FineMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class FineServiceImpl implements FineService {

    private FineRepository fineRepository;

    @Override
    public AddFineResponse add(AddFineRequest request) {
        Fine fine = FineMapper.INSTANCE.fineFromAddRequest(request);
        fine.setCreatedAt(LocalDate.now());
        Fine savedFine = fineRepository.save(fine);

        return FineMapper.INSTANCE.addResponseFromFine(savedFine);
    }

    @Override
    public Fine findCurrentFineAmount() {
        return fineRepository.findCurrentFineAmount();
    }
}
