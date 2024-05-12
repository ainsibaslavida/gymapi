package com.squares.gymapi.services;

import com.squares.gymapi.domain.Member;
import com.squares.gymapi.dto.ResponseDTO;
import com.squares.gymapi.dto.RequestDTO;
import com.squares.gymapi.exceptions.MemberAlreadyExistsException;
import com.squares.gymapi.exceptions.MemberNotExistsException;
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
            throw new MemberAlreadyExistsException();
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

    public void delete(String id) {
        Optional<Member> optionalMember = this.memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new MemberNotExistsException();
        }

        this.memberRepository.deleteById(id);
    }

    public ResponseDTO get(String id) {
        Optional<Member> memberExists = this.memberRepository.findById(id);

        if (memberExists.isEmpty()) {
            throw new MemberNotExistsException();
        }

        Member receivedMember = memberExists.get();

        return new ResponseDTO(receivedMember.getCpf(), receivedMember.getName(), receivedMember.getAge(), receivedMember.getAddress(), receivedMember.getPhone(), receivedMember.getPlan(), receivedMember.getCreatedAt(), receivedMember.getLastUpdate(), receivedMember.getActive());
    }

    public void update(String id, Member member) {
        Optional<Member> optionalMember = this.memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new MemberNotExistsException();
        }

        /*
        * O objeto retornado é usado como base para atualização.
        * Os dados CPF e created_at são repassados.
        * */
        Member updateMember = optionalMember.get();

        updateMember.setName(member.getName());
        updateMember.setAge(member.getAge());
        updateMember.setAddress(member.getAddress());
        updateMember.setPhone(member.getPhone());
        updateMember.setPlan(member.getPlan());
        updateMember.setLastUpdate(LocalDateTime.now());
        updateMember.setActive(member.getActive());

        this.memberRepository.save(updateMember);
    }
}
