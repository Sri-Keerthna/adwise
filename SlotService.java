package com.spiralforge.adwise.service;

import java.util.List;

import javax.validation.Valid;

import com.spiralforge.adwise.dto.SlotResponseDto;
import com.spiralforge.adwise.exception.SlotNotFoundException;
import com.spiralforge.adwise.util.AddWiseEnum.SlotStatus;

public interface SlotService {

	List<SlotResponseDto> getSlotDetails(@Valid String date, SlotStatus slotStatus) throws SlotNotFoundException;

}
