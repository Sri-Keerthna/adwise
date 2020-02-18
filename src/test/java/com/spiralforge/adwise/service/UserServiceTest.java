package com.spiralforge.adwise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

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
import com.spiralforge.adwise.util.AddWiseEnum.SlotStatus;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	BookingRepository bookingRepository;

	@Mock
	SlotRepository slotRepository;

	@Mock
	UserRepository userRepository;

	BookedSlotResponseDto bookedSlotResponseDto = new BookedSlotResponseDto();
	Role role = Role.ADMIN;
	User user = new User();
	LocalDate localDate = LocalDate.parse("2020-02-17");
	List<Slot> slots = new ArrayList<>();
	Slot slot = new Slot();
	List<Booking> bookedSlots = new ArrayList<>();
	Booking booking = new Booking();
	List<BookedSlotResponseDto> responseList = new ArrayList<>();
	BookedResponseDto bookedResponseDto = new BookedResponseDto();
	List<BookedResponseDto> bookedlist = new ArrayList<>();
	SlotStatus slotStatus = SlotStatus.AVAILABLE;
			
	@Before
	public void setUp() {
		slot.setSlotId(1L);
		slot.setSlotDate(localDate);
		slot.setSlotFromTime(LocalTime.of(11, 00, 00));
		slot.setSlotToTime(LocalTime.of(12, 00, 00));
		slot.setUser(user);
		slot.setSlotStatus(slotStatus);
		slots.add(slot);
		booking.setSlotDate(LocalDate.of(2020, 02, 17));
		booking.setBookingId(1L);
		booking.setPrice(200D);
		booking.setSlot(slot);
		booking.setUser(user);
		booking.setSlotFromTime(LocalTime.of(11, 00, 00));
		booking.setSlotToTime(LocalTime.of(12, 00, 00));
		bookedSlots.add(booking);
		user.setUserId(1L);
		user.setMobileNumber(453782975L);
		user.setPassword("sri");
		user.setUserName("sri");
		user.setRole(role);
		BeanUtils.copyProperties(bookedSlots, bookedSlotResponseDto);
		responseList.add(bookedSlotResponseDto);
		BeanUtils.copyProperties(slot, bookedResponseDto);
		bookedResponseDto.setSlotId(slot.getSlotId());
		bookedResponseDto.setBookedSlot(responseList);
		bookedlist.add(bookedResponseDto);
	}

	@Test
	public void testGetBookedSlotsPositive() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findByUserIdAndRole(1L, role)).thenReturn(Optional.of(user));
		Mockito.when(slotRepository.findBySlotDate(localDate)).thenReturn(slots);
		Mockito.when(bookingRepository.findBySlotDateAndSlot(localDate, slot)).thenReturn(bookedSlots);
		List<BookedResponseDto> bookedResponseDto = userService.getBookedSlots(1L, "2020-02-17");
		assertEquals(1, bookedResponseDto.size());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testGetBookedSlotsNegativeException() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findByUserIdAndRole(2L, role)).thenReturn(Optional.of(user));
		userService.getBookedSlots(1L, "2020-02-17");
	}
	
	@Test(expected = SlotNotFoundException.class)
	public void testGetBookedSlotsNegative() throws UserNotFoundException, SlotNotFoundException {
		localDate = LocalDate.parse("2020-02-02");
		Mockito.when(userRepository.findByUserIdAndRole(1L, role)).thenReturn(Optional.of(user));
		Mockito.when(slotRepository.findBySlotDate(localDate)).thenReturn(slots);
		userService.getBookedSlots(1L, "2020-02-17");
	}

}
