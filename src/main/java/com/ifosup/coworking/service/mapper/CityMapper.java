package com.ifosup.coworking.service.mapper;

import com.ifosup.coworking.domain.City;
import com.ifosup.coworking.dto.CityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDto, City> {

    City toEntity(CityDto cityDto);
}
