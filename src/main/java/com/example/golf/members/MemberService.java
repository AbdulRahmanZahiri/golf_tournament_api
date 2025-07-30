package com.example.golf.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member updateMember(Long id, Member updated) {
        return memberRepository.findById(id).map(member -> {
            member.setFirstName(updated.getFirstName());
            member.setLastName(updated.getLastName());
            member.setAddress(updated.getAddress());
            member.setEmail(updated.getEmail());
            member.setPhoneNum(updated.getPhoneNum());
            member.setStartDate(updated.getStartDate());
            member.setDurationInMonths(updated.getDurationInMonths());
            return memberRepository.save(member);
        }).orElse(null);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> searchByName(String firstName, String lastName) {
        return memberRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

    public List<Member> searchByStartDate(LocalDate date) {
        return memberRepository.findByStartDate(date);
    }

    public Optional<Member> searchByPhoneNum(String phoneNum) {
        return memberRepository.findByPhoneNum(phoneNum);
    }

    public Optional<Member> searchByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
