package com.spiralforge.adwise.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spiralforge.adwise.dto.BookedResponseDto;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.exception.UserNotFoundException;
import com.spiralforge.adwise.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserContoller {

	@Autowired
	UserService userService;

	/**
	 * @author Sri Keerthna.
	 * @since 2020-02-17. In this method all the booked slots are fetched from
	 *        database.
	 * @param userId for checking whether user is admin or sales person.
	 * @param date   for particular date
	 * @return list of booked slots on that particular date.
	 * @throws UserNotFoundException if user is not available.
	 * @throws SlotNotFoundException if slot is unavailable.
	 */
	@GetMapping
	public ResponseEntity<List<BookedResponseDto>> getBookedSlots(@Valid @RequestParam Long userId,
			@RequestParam String date) throws UserNotFoundException, SlotNotFoundException {
		List<BookedResponseDto> responseDto = userService.getBookedSlots(userId, date);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}
}
