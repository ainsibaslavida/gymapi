package com.squares.gymapi.repositories;

import com.squares.gymapi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
