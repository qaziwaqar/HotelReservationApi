package com.waqar.reservation;

import com.waqar.reservation.data.entity.Guest;
import com.waqar.reservation.data.entity.Reservation;
import com.waqar.reservation.data.entity.Room;
import com.waqar.reservation.data.repositories.GuestRepository;
import com.waqar.reservation.data.repositories.ReservationRepository;
import com.waqar.reservation.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class ApiStartup implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(ApiStartup.class.getSimpleName());
	private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private ReservationRepository reservationRepository;

	@Autowired
	public ApiStartup(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	@Autowired


	public static void main(String[] args) {
		SpringApplication.run(ApiStartup.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Application startup with option names: " + Arrays.toString(args));

		LOGGER.info("Loading some dummy data ");
		List<Room> roomList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Room room = new Room();
			room.setNumber("AB"+ i);
			int randomBeds = ((int)Math.random()*5)+5;
			room.setTotalBeds(randomBeds);
			roomList.add( this.roomRepository.save(room) );
		}

		List<Guest> guestList = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			Guest guest = new Guest();
			int randomAge = ((int)Math.random()*40)+40;
			guest.setFirstName("Johny " + i);
			guest.setLastName("Test " + i);
			guest.setEmail("johnytest"+i+"@gmail.com");
			guest.setAge(randomAge);
			guestList.add( this.guestRepository.save(guest) );
		}

		for (int i = 0; i < guestList.size(); i++) {
			Reservation reservation = new Reservation();
			reservation.setGuestId(guestList.get(i).getId());
			reservation.setRoomId(roomList.get(i).getId());
			reservation.setReservationDate(DATE_FORMAT.parse("2019-07-0"+(i+1)));
			this.reservationRepository.save(reservation);
		}

		LOGGER.info("Loading dummy data ended");
	}
}
