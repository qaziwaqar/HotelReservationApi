package com.waqar.reservation.web.api;

import com.waqar.reservation.business.service.RoomService;
import com.waqar.reservation.data.entity.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {

    @Autowired
    private RoomController roomController;

    @MockBean
    private RoomService roomService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        Assert.assertNotNull(roomController);
    }

    @Test
    public void getAllRooms() throws Exception {
        List<Room> roomList = new ArrayList<>();
        Room roomMock = new Room();
        roomMock.setId(1);
        roomMock.setNumber("AB1");
        roomMock.setTotalBeds(2);
        roomMock.setRemarks("Excellent");
        roomList.add(roomMock);

        BDDMockito.given(roomService.getAllAvailableRooms()).willReturn(roomList);
        this.mockMvc.perform(get("/api/room/all")).andExpect(status().isOk()).andExpect(content().string(containsString("AB1")));
    }

}
