package com.squares.gymapi.controllers;

import com.squares.gymapi.domain.Member;
import com.squares.gymapi.dto.ResponseDTO;
import com.squares.gymapi.infra.response.ErrorMessage;
import com.squares.gymapi.dto.IdentifierDTO;
import com.squares.gymapi.dto.MessageResponseDTO;
import com.squares.gymapi.dto.RequestDTO;
import com.squares.gymapi.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> create(@RequestBody @Valid RequestDTO memberRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        this.memberService.create(memberRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("User created successfully!"));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseDTO>> list() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.memberService.list());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponseDTO> delete(@RequestBody @Valid IdentifierDTO memberIdentifierDTO) {
        this.memberService.delete(memberIdentifierDTO.cpf());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponseDTO("The member has been deleted."));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> get(@PathVariable String cpf) {
        ResponseDTO memberDetailsDTO = this.memberService.get(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(memberDetailsDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponseDTO> update(@RequestBody @Valid RequestDTO member) {
        this.memberService.update(member.cpf(), requestToMember(member));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponseDTO("Member information has been updated."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, "The input object has errors.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    private Member requestToMember(RequestDTO request) {
        Member requestToMember = new Member();

        requestToMember.setCpf(request.cpf());
        requestToMember.setName(request.name());
        requestToMember.setAge(request.age());
        requestToMember.setAddress(request.address());
        requestToMember.setPhone(request.phone());
        requestToMember.setPlan(request.plan());
        requestToMember.setActive(request.active());

        return requestToMember;
    }
}
