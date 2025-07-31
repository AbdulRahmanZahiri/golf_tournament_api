package com.example.golf.members;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllMembers() throws Exception {
        // Fixed constructor: firstName, lastName, address, email, phoneNum, startDate, durationInMonths
        Member member = new Member("John", "Doe", "123 Street", "john@example.com", "555-1234", LocalDate.now(), 12);
        Mockito.when(memberService.getAllMembers()).thenReturn(List.of(member));

        mockMvc.perform(get("/api/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void shouldReturnMemberById() throws Exception {
        long id = 1L;
        // Fixed constructor
        Member member = new Member("John", "Doe", "123 Street", "john@example.com", "555-1234", LocalDate.now(), 12);
        Mockito.when(memberService.getMemberById(id)).thenReturn(Optional.of(member));

        mockMvc.perform(get("/api/members/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldCreateMember() throws Exception {
        // Fixed constructor
        Member member = new Member("Jane", "Smith", "456 Avenue", "jane@example.com", "555-5678", LocalDate.now(), 6);
        Mockito.when(memberService.createMember(Mockito.any(Member.class))).thenReturn(member);

        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }
}