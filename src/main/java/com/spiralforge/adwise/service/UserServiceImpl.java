package com.spiralforge.adwise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiralforge.adwise.constants.ApiConstant;
import com.spiralforge.adwise.dto.BookedResponseDto;
import com.spiralforge.adwise.dto.BookedSlotResponseDto;
import com.spiralforge.adwise.entity.Booking;
import com.spiralforge.adwise.entity.Slot;
import com.spiralforge.adwise.entity.User;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.exception.UserNotFoundException;
import com.spiralforge.adwise.repository.BookingRepository;
import com.spiralforge.adwise.repository.SlotRepository;
import com.spiralforge.adwise.repository.UserRepository;
import com.spiralforge.adwise.util.AddWiseEnum.Role;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	UserRepository userRepository;

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
	@Override
	public List<BookedResponseDto> getBookedSlots(@Valid Long userId, String date)
			throws UserNotFoundException, SlotNotFoundException {
		LocalDate localDate = LocalDate.parse(date);
		Role role = Role.ADMIN;
		Optional<User> user = userRepository.findByUserIdAndRole(userId, role);
		if (!user.isPresent()) {
			throw new UserNotFoundException(ApiConstant.USER_NOT_FOUND_EXCEPTION);
		}
		List<Slot> slots = slotRepository.findBySlotDate(localDate);
		if (slots.isEmpty()) {
			throw new SlotNotFoundException(ApiConstant.SLOT_NOT_FOUND_EXCEPTION);
		}
		List<BookedResponseDto> bookedlist = new ArrayList<>();
		slots.forEach(slot -> {
			List<BookedSlotResponseDto> responseList = new ArrayList<>();
			BookedResponseDto bookedResponseDto = new BookedResponseDto();
			List<Booking> bookedSlots = bookingRepository.findBySlotDateAndSlot(localDate, slot);
			bookedSlots.forEach(bookedSlot -> {
				BookedSlotResponseDto bookedSlotResponseDto = new BookedSlotResponseDto();
				BeanUtils.copyProperties(bookedSlot, bookedSlotResponseDto);
				responseList.add(bookedSlotResponseDto);
				BeanUtils.copyProperties(slot, bookedResponseDto);
			});
			bookedResponseDto.setSlotId(slot.getSlotId);                                                                                     ));
			bookedResponseDto.setBookedSlot(responseList);
			bookedlist.add(bookedResponseDto);
		});
		return bookedlist;
	}

}
