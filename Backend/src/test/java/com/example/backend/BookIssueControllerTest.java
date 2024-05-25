package com.example.backend;

import com.example.backend.Controllers.BookIssueController;
import com.example.backend.Entities.BookIssueHistory;
import com.example.backend.Services.BookIssueHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookIssueControllerTest {

    @Mock
    private BookIssueHistoryService bookIssueHistoryService;

    @InjectMocks
    private BookIssueController bookIssueController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBookIssue() {
        BookIssueHistory bookIssueHistory = new BookIssueHistory();
        when(bookIssueHistoryService.saveBookIssueHistory(bookIssueHistory, 1L, 2L, 3L)).thenReturn("Book Successfully issue");

        ResponseEntity<?> responseEntity = bookIssueController.bookissue(bookIssueHistory, 1L, 2L, 3L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book Successfully issue", responseEntity.getBody());
        verify(bookIssueHistoryService, times(1)).saveBookIssueHistory(bookIssueHistory, 1L, 2L, 3L);
    }

    @Test
    public void testShowIssueBooks() {
        when(bookIssueHistoryService.getAllBookIssueHistory()).thenReturn(Collections.emptyList());

        ResponseEntity<?> responseEntity = bookIssueController.showissuebooks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
        verify(bookIssueHistoryService, times(1)).getAllBookIssueHistory();
    }

    @Test
    public void testReturnBook() {
        when(bookIssueHistoryService.setreturnbook(1L)).thenReturn("Successfully update return date");

        ResponseEntity<?> responseEntity = bookIssueController.returnbook(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully update return date", responseEntity.getBody());
        verify(bookIssueHistoryService, times(1)).setreturnbook(1L);
    }

    @Test
    public void testPayPenalty() {
        when(bookIssueHistoryService.paypenalty(1L)).thenReturn("Successfully payment done.");

        ResponseEntity<?> responseEntity = bookIssueController.paypenalty(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully payment done.", responseEntity.getBody());
        verify(bookIssueHistoryService, times(1)).paypenalty(1L);
    }
}

