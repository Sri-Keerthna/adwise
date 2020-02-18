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

import com.spiralforge.adwise.dto.SlotResponseDto;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.service.SlotService;
import com.spiralforge.adwise.util.AddWiseEnum.SlotStatus;

@RestController
@RequestMapping("/slots")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class SlotController {

	@Autowired
	SlotService slotService;

	/**
	 * @author Sri Keerthna.
	 * @since 2020-02-17. In tis method all the available, partially available,
	 *        booked slots will be shown.
	 * @param date       given by user.
	 * @param slotStatus as available, partially available, booked slots.
	 * @return list of slots.
	 * @throws SlotNotFoundException if slots are unavailable.
	 */
	@GetMapping
	public ResponseEntity<List<SlotResponseDto>> getSlotList(@Valid @RequestParam String date,
			@RequestParam SlotStatus slotStatus) throws SlotNotFoundException {
		List<SlotResponseDto> slotResponseDto = slotService.getSlotDetails(date, slotStatus);
		return new ResponseEntity<>(slotResponseDto, HttpStatus.OK);

	}
}
