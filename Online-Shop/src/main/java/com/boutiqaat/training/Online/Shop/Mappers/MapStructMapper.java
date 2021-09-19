package com.boutiqaat.training.Online.Shop.Mappers;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.boutiqaat.training.Online.Shop.Entities.User;
import com.boutiqaat.training.Online.Shop.mapstruct.dtos.UserPostDto;


@Mapper(
	    componentModel = "spring"
		)
public interface MapStructMapper {

	

	 User userPostDtoToUser(UserPostDto userPostDto);
	
}
