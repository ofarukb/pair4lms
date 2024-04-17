package com.tobeto.java4a.pair4lms.services.mappers;

import com.tobeto.java4a.pair4lms.entities.Fine;
import com.tobeto.java4a.pair4lms.services.dtos.requests.fines.AddFineRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.fines.AddFineResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FineMapper {
	FineMapper INSTANCE = Mappers.getMapper(FineMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	Fine fineFromAddRequest(AddFineRequest request);

	AddFineResponse addResponseFromFine(Fine fine);

}
