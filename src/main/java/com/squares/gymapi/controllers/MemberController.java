package com.squares.gymapi.controllers;

import com.squares.gymapi.dto.MemberDetailsDTO;
import com.squares.gymapi.dto.MemberIdentifierDTO;
import com.squares.gymapi.dto.MemberMessageDTO;
import com.squares.gymapi.dto.MemberRequestDTO;
import com.squares.gymapi.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<MemberDetailsDTO> create(@RequestBody @Valid MemberRequestDTO memberRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        MemberDetailsDTO createdMember = this.memberService.create(memberRequestDTO);
        var uri = uriComponentsBuilder.path("/member/{cpf}").buildAndExpand(createdMember.cpf()).toUri();
        return ResponseEntity.created(uri).body(createdMember);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MemberDetailsDTO>> getAll() {
        return ResponseEntity.accepted().body(this.memberService.getAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MemberMessageDTO> delete(@RequestBody MemberIdentifierDTO memberIdentifierDTO) {
        MemberIdentifierDTO removed = this.memberService.delete(memberIdentifierDTO);
        return ResponseEntity.ok(new MemberMessageDTO("The member has been deleted.", removed.cpf()));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<MemberDetailsDTO> get(@PathVariable String cpf) {
        MemberDetailsDTO memberDetailsDTO = this.memberService.get(cpf);

        return ResponseEntity.accepted().body(memberDetailsDTO);
    }
}
