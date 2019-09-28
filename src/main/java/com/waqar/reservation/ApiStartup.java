package com.waqar.reservation;

import com.waqar.reservation.data.entity.Room;
import com.waqar.reservation.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.logging.Logger;

@SpringBootApplication
public class ApiStartup implements CommandLineRunner {

	public static final Logger LOGGER = Logger.getLogger(ApiStartup.class.getSimpleName());
	private RoomRepository roomRepository;

	@Autowired
	public ApiStartup(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiStartup.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Application startup with option names: " + Arrays.toString(args));

		LOGGER.info("Loading some dummy data ");
		for (int i = 1; i <= 10; i++) {
			Room room = new Room();
			room.setNumber("AB"+ i);
			int randomBeds = ((int)Math.random()*5)+5 ;
			room.setTotalBeds(randomBeds);
			this.roomRepository.save(room);
		}

		LOGGER.info("Loading dummy data ended");
	}
}
