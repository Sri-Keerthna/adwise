package com.spiralforge.adwise.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spiralforge.adwise.entity.Slot;
import com.spiralforge.adwise.util.AddWiseEnum.SlotStatus;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

	List<Slot> findBySlotDateAndSlotStatus(LocalDate localDate, SlotStatus slotStatus);

	List<Slot> findBySlotDate(LocalDate localDate);

}
