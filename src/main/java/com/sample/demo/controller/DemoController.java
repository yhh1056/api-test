package com.sample.demo.controller;

import com.sample.demo.dto.MemberDto;
import com.sample.demo.model.Member;
import com.sample.demo.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhh1056
 * @since 2021/01/28
 */

@RestController
public class DemoController {
    
    private final MemberService memberService;
    
    public DemoController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/api")
    public ResponseEntity<Page<Member>> getPage(Pageable pageable) {
        final Page<Member> page = memberService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/api/members")
    public ResponseEntity<String> createUser(@RequestBody MemberDto memberDto) {

        Member member = memberDto.toEntity();
        memberService.save(member);
        return ResponseEntity.ok("resource update");
    }

    @GetMapping("/api/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.findById(id).orElse(new Member());
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseEntity.ok("resource delete");
    }
}
