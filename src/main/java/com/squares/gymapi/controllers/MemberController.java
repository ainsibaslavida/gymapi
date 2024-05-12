package com.squares.gymapi.controllers;

import com.squares.gymapi.dto.ResponseDTO;
import com.squares.gymapi.dto.IdentifierDTO;
import com.squares.gymapi.dto.MessageResponseDTO;
import com.squares.gymapi.dto.RequestDTO;
import com.squares.gymapi.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(new MessageResponseDTO("User created successfully!"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseDTO>> list() {
        return new ResponseEntity<>(this.memberService.list(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponseDTO> delete(@RequestBody IdentifierDTO memberIdentifierDTO) {
        this.memberService.delete(memberIdentifierDTO.cpf());
        return new ResponseEntity<>(new MessageResponseDTO("The member has been deleted."), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> get(@PathVariable String cpf) {
        ResponseDTO memberDetailsDTO = this.memberService.get(cpf);

        return new ResponseEntity<>(memberDetailsDTO, HttpStatus.OK);
    }
}
