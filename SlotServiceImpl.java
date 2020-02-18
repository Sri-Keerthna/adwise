package com.spiralforge.adwise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiralforge.adwise.constants.ApiConstant;
import com.spiralforge.adwise.dto.SlotResponseDto;
import com.spiralforge.adwise.entity.Slot;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.repository.PlanRepository;
import com.spiralforge.adwise.repository.SlotRepository;
import com.spiralforge.adwise.util.AddWiseEnum.SlotStatus;

@Service
public class SlotServiceImpl implements SlotService {

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	PlanRepository planRepository;

	/**
	 * @author Sri Keerthna.
	 * @since 2020-02-17. In tis method all the available, partially available,
	 *        booked slots will be shown.
	 * @param date       given by user.
	 * @param slotStatus as available, partially available, booked slots.
	 * @return list of slots.
	 * @throws SlotNotFoundException if slots are unavailable.
	 */
	@Override
	public List<SlotResponseDto> getSlotDetails(@Valid String date, SlotStatus slotStatus)
			throws SlotNotFoundException {
		LocalDate localDate = LocalDate.parse(date);
		List<Slot> slots = slotRepository.findBySlotDateAndSlotStatus(localDate, slotStatus);
		if (slots.isEmpty()) {
			throw new SlotNotFoundException(ApiConstant.SLOT_NOT_FOUND_EXCEPTION);
		}
		List<SlotResponseDto> responseList = new ArrayList<>();
		slots.forEach(slot -> {
			SlotResponseDto slotResponseDto = new SlotResponseDto();
			slotResponseDto.setPlanName(slot.getPlan().getPlanName());
			BeanUtils.copyProperties(slot, slotResponseDto);
			responseList.add(slotResponseDto);
		});
		return responseList;
	}

}
