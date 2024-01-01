package com.signin.request.mapper;

import org.mapstruct.*;

import com.signin.request.entity.AuthUser;
import com.signin.request.payload.request.AuthsignUpRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CreateNewUserMapper {

	@Mapping(target = "confirmPassword", ignore = true)
	void createNewUserFromRequestPayload(AuthUser authUser, @MappingTarget AuthsignUpRequest authSignupRequestPayload);

}
