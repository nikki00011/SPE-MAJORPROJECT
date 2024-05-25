package com.example.backend;

import com.example.backend.Controllers.BookController;
import com.example.backend.Entities.BooksInformation;
import com.example.backend.Services.BookInformationService;
import com.example.backend.Services.BooksService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookInformationService bookInformationService;

    @MockBean
    private BooksService booksService;

    @Test
    public void testAddBook() throws Exception {
        BooksInformation book = new BooksInformation();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setIsbn("1234567890");
        book.setCategory("Category");
        book.setStock(10L);
        book.setAvailable(10L);
        book.setImage("image_url");

        Mockito.when(bookInformationService.saveBooksInformation(any(BooksInformation.class)))
                .thenReturn("Book sucessfully added");

        mockMvc.perform(MockMvcRequestBuilders.post("/book/addbook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Book\",\"author\":\"Author\",\"isbn\":\"1234567890\",\"category\":\"Category\",\"stock\":10,\"available\":10,\"image\":\"image_url\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book sucessfully added"));
    }

    @Test
    public void testGetBooks() throws Exception {
        BooksInformation book = new BooksInformation();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setIsbn("1234567890");
        book.setCategory("Category");
        book.setStock(10L);
        book.setAvailable(10L);
        book.setImage("image_url");

        Mockito.when(bookInformationService.getAllBooksInformation())
                .thenReturn(Collections.singletonList(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/getbooks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"title\":\"Test Book\",\"author\":\"Author\",\"isbn\":\"1234567890\",\"category\":\"Category\",\"stock\":10,\"available\":10,\"image\":\"image_url\",\"books\":null}]"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.when(bookInformationService.deleteBooksInformation(anyLong()))
                .thenReturn("Delete Successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/deletebook/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete Successfully"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BooksInformation book = new BooksInformation();
        book.setId(1L);
        book.setTitle("Updated Book");
        book.setAuthor("Updated Author");
        book.setIsbn("1234567890");
        book.setCategory("Updated Category");
        book.setStock(20L);
        book.setAvailable(20L);
        book.setImage("updated_image_url");

        Mockito.when(bookInformationService.updateBooksInfromation(any(BooksInformation.class), anyLong()))
                .thenReturn("Book is successfully Updated");

        mockMvc.perform(MockMvcRequestBuilders.put("/book/updatebook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\",\"author\":\"Updated Author\",\"isbn\":\"1234567890\",\"category\":\"Updated Category\",\"stock\":20,\"available\":20,\"image\":\"updated_image_url\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book is successfully Updated"));
    }

    @Test
    public void testDeleteOneBook() throws Exception {
        Mockito.when(booksService.deleteBook(anyLong(), anyLong()))
                .thenReturn("true");

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/deletebook/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
