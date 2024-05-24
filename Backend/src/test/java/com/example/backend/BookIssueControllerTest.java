package com.example.backend.Controllers;

import com.example.backend.Entities.BookIssueHistory;
import com.example.backend.Services.BookIssueHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookIssueControllerTest {

    @Mock
    private BookIssueHistoryService bookIssueHistoryService;

    @InjectMocks
    private BookIssueController bookIssueController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookIssueController).build();
    }

    @Test
    public void testBookIssue() throws Exception {
        BookIssueHistory bookIssueHistory = new BookIssueHistory();
        // Set bookIssueHistory attributes here

        when(bookIssueHistoryService.saveBookIssueHistory(any(BookIssueHistory.class), any(Long.class), any(Long.class), any(Long.class)))
                .thenReturn(ResponseEntity.ok("Book issued successfully"));

        mockMvc.perform(post("/issue/bookissue/1/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookIssueHistory)))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowIssueBooks() throws Exception {
        when(bookIssueHistoryService.getAllBookIssueHistory()).thenReturn(/* Mock list of issue history */);

        mockMvc.perform(get("/issue/showissuebooks"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnBook() throws Exception {
        when(bookIssueHistoryService.setreturnbook(any(Long.class)))
                .thenReturn(ResponseEntity.ok("Book returned successfully"));

        mockMvc.perform(put("/issue/returnbook/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPayPenalty() throws Exception {
        when(bookIssueHistoryService.paypenalty(any(Long.class)))
                .thenReturn(ResponseEntity.ok("Penalty paid successfully"));

        mockMvc.perform(put("/issue/paypenalty/1"))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

