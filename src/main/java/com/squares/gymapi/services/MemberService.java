package com.squares.gymapi.services;

import com.squares.gymapi.domain.Member;
import com.squares.gymapi.dto.member.ResponseDTO;
import com.squares.gymapi.dto.member.IdentifierDTO;
import com.squares.gymapi.dto.member.RequestDTO;
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

    public void create(RequestDTO memberRequestDTO) {
        Optional<Member> optionalMember = this.memberRepository.findById(memberRequestDTO.cpf());

        if (optionalMember.isPresent()) {
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
                LocalDateTime.now(),
                Boolean.TRUE
        );

        this.memberRepository.save(createMember);
    }

    public List<ResponseDTO> list() {
        List<Member> members = this.memberRepository.findAll();

        return members.stream().map(member -> new ResponseDTO(
                member.getCpf(),
                member.getName(),
                member.getAge(),
                member.getAddress(),
                member.getPhone(),
                member.getPlan(),
                member.getCreatedAt(),
                member.getLastUpdate(),
                member.getActive())
        ).toList();
    }

    public void delete(IdentifierDTO memberIdentifierDTO) {
        Optional<Member> optionalMember = this.memberRepository.findById(memberIdentifierDTO.cpf());

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("The member does not exists.");
        }

        this.memberRepository.deleteById(memberIdentifierDTO.cpf());
    }

    public ResponseDTO get(String cpf) {
        Optional<Member> memberExists = this.memberRepository.findById(cpf);

        if (memberExists.isEmpty()) {
            throw new RuntimeException("The member does not exists.");
        }

        Member receivedMember = memberExists.get();

        return new ResponseDTO(receivedMember.getCpf(), receivedMember.getName(), receivedMember.getAge(), receivedMember.getAddress(), receivedMember.getPhone(), receivedMember.getPlan(), receivedMember.getCreatedAt(), receivedMember.getLastUpdate(), receivedMember.getActive());
    }
}
