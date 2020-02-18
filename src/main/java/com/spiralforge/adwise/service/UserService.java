package com.spiralforge.adwise.service;

import java.util.List;

import javax.validation.Valid;

import com.spiralforge.adwise.dto.BookedResponseDto;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.exception.UserNotFoundException;

public interface UserService {

	List<BookedResponseDto> getBookedSlots(@Valid Long userId, String date) throws UserNotFoundException, SlotNotFoundException;

}
