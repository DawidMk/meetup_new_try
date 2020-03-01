package pl.dma.appka.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.dma.appka.event.Event;
import pl.dma.appka.event.EventService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    EventService eventService;
    @Mock
    Model model;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(eventService);
    }

    @Test
    public void indexIsLoaded() {
        assertThat(indexController).isNotNull();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndex() {
        //given
        Event event = Event.builder()
                .id(1)
                .eventName("event name")
                .description("description")
                .startDate(LocalDate.parse("2021-01-01"))
                .endDate(LocalDate.parse("2021-01-02"))
                .build();
        List<Event> eventList = Arrays.asList(event);
        when(eventService.findAllEvents()).thenReturn(eventList);
        ArgumentCaptor<List<Event>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        //when
        String index = indexController.index(model);
        //then
        Assert.assertEquals("index", index);
        verify(eventService, times(1)).findAllEvents();
        verify(model, times(1)).addAttribute(eq("events"), argumentCaptor.capture());
        List<Event> listInController = argumentCaptor.getValue();
        Assert.assertEquals(1, listInController.size());
    }
}