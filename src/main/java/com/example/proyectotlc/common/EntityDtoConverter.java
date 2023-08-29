package com.example.proyectotlc.common;



import com.example.proyectotlc.dto.SignupRequest;
import com.example.proyectotlc.dto.User;
import com.example.proyectotlc.dto.UserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class EntityDtoConverter {


	private ModelMapper modelMapper;

	@Autowired
	public EntityDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public UserRequest convertEntityToDto(User user) {
		return modelMapper.map(user, UserRequest.class);
	}
	

	
	public User convertDtoToEntity(UserRequest userRequest) {
		return modelMapper.map(userRequest, User.class);
	}
	
	public User convertDtoToEntity(SignupRequest signupRequest) {
		return modelMapper.map(signupRequest, User.class);
	}

	
}
