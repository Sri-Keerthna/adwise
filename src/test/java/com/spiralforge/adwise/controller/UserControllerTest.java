package com.spiralforge.adwise.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.spiralforge.adwise.dto.BookedResponseDto;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.exception.UserNotFoundException;
import com.spiralforge.adwise.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	@InjectMocks
	UserContoller userController;

	@Mock
	UserService userService;
	
	List<BookedResponseDto> responseDto = new ArrayList<>();
	
	@Test
	public void testGetBookedSlots() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userService.getBookedSlots(1L, "2020-02-17")).thenReturn(responseDto);
		ResponseEntity<List<BookedResponseDto>> list = userController.getBookedSlots(1L, "2020-02-17");
		assertEquals(200, list.getStatusCodeValue());
	}
}
