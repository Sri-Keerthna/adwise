package com.spiralforge.adwise.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spiralforge.adwise.entity.Booking;
import com.spiralforge.adwise.entity.Slot;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findBySlot(Slot slot);

	List<Booking> findBySlotDateAndSlot(LocalDate localDate, Slot slot);

}
