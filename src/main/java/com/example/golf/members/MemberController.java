package com.example.golf.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member updated) {
        return memberService.updateMember(id, updated);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping("/search/name")
    public List<Member> searchByName(@RequestParam String firstName, @RequestParam String lastName) {
        return memberService.searchByName(firstName, lastName);
    }

    @GetMapping("/search/startDate")
    public List<Member> searchByStartDate(@RequestParam String date) {
        return memberService.searchByStartDate(LocalDate.parse(date));
    }

    @GetMapping("/search/phone")
    public Optional<Member> searchByPhone(@RequestParam String phoneNum) {
        return memberService.searchByPhoneNum(phoneNum);
    }

    @GetMapping("/search/email")
    public Optional<Member> searchByEmail(@RequestParam String email) {
        return memberService.searchByEmail(email);
    }
}
