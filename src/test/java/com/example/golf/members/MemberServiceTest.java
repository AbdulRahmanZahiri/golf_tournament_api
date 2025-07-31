package com.example.golf.members;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org. mockito.InjectMocks;
import org. mockito.Mock;
import org. mockito.MockitoAnnotations;

import java. time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org. mockito.Mockito.*;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        member = new Member("John", "Doe", "123 Main St", "john@example.com", "555-1234", LocalDate.now(), 12);
    }

    @Test
    void testCreateMember() {
        when(memberRepository.save(member)).thenReturn(member);
        Member saved = memberService.createMember(member);
        assertEquals("John", saved.getFirstName());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    void testGetAllMembers() {
        when(memberRepository.findAll()).thenReturn(List.of(member));
        List<Member> result = memberService.getAllMembers();
        assertEquals(1, result.size());
    }

    @Test
    void testGetMemberById() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Optional<Member> result = memberService.getMemberById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateMember() {
        Member updated = new Member("Jane", "Smith", "456 Side St", "jane@example.com", "555-5678", LocalDate.now(), 6);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenReturn(updated);

        Member result = memberService.updateMember(1L, updated);
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void testDeleteMember() {
        doNothing().when(memberRepository).deleteById(1L);
        memberService.deleteMember(1L);
        verify(memberRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSearchByEmail() {
        when(memberRepository.findByEmail("john@example.com")).thenReturn(Optional.of(member));
        Optional<Member> result = memberService.searchByEmail("john@example.com");
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }
}
