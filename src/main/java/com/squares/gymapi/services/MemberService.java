package com.squares.gymapi.services;

import com.squares.gymapi.domain.Member;
import com.squares.gymapi.dto.MemberDetailsDTO;
import com.squares.gymapi.dto.MemberIdentifierDTO;
import com.squares.gymapi.dto.MemberRequestDTO;
import com.squares.gymapi.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDetailsDTO create(MemberRequestDTO memberRequestDTO) {
        Optional<Member> member = this.memberRepository.findById(memberRequestDTO.cpf());

        if (member.isPresent()) {
            throw new RuntimeException("The member already exists.");
        }

        Member createMember = new Member(
                memberRequestDTO.cpf(),
                memberRequestDTO.name(),
                memberRequestDTO.age(),
                memberRequestDTO.address(),
                memberRequestDTO.phone(),
                memberRequestDTO.plan(),
                LocalDateTime.now(),
                Boolean.TRUE
        );

        Member createdMember = this.memberRepository.save(createMember);

        return new MemberDetailsDTO(createdMember.getCpf(), createdMember.getName(), createdMember.getAge(), createdMember.getAddress(), createdMember.getPhone(), createdMember.getPlan(), createdMember.getCreatedAt(), createdMember.getActive());
    }

    public List<MemberDetailsDTO> getAll() {
        List<Member> members = this.memberRepository.findAll();

        return members.stream().map(member -> new MemberDetailsDTO(
                member.getCpf(),
                member.getName(),
                member.getAge(),
                member.getAddress(),
                member.getPhone(),
                member.getPlan(),
                member.getCreatedAt(),
                member.getActive())
        ).toList();
    }

    public MemberIdentifierDTO delete(MemberIdentifierDTO memberIdentifierDTO) {
        Optional<Member> memberExists = this.memberRepository.findById(memberIdentifierDTO.cpf());

        if (memberExists.isEmpty()) {
            throw new RuntimeException("The user does not exists.");
        }

        this.memberRepository.deleteById(memberIdentifierDTO.cpf());
        return memberIdentifierDTO;
    }

    public MemberDetailsDTO get(String cpf) {
        Optional<Member> memberExists = this.memberRepository.findById(cpf);

        if (memberExists.isEmpty()) {
            throw new RuntimeException("The member does not exists.");
        }

        Member receivedMember = memberExists.get();

        return new MemberDetailsDTO(receivedMember.getCpf(), receivedMember.getName(), receivedMember.getAge(), receivedMember.getAddress(), receivedMember.getPhone(), receivedMember.getPlan(), receivedMember.getCreatedAt(), receivedMember.getActive());
    }
}
