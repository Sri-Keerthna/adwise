package com.spiralforge.adwise.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.spiralforge.adwise.util.AddWiseEnum.SlotStatus;

import lombok.Data;

@Data
public class SlotResponseDto {

	private Long slotId;
	private LocalDate slotDate;
	private LocalTime slotFromTime;
	private LocalTime slotToTime;
	private SlotStatus slotStatus;
	private String planName;
}
