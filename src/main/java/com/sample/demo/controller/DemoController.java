package com.sample.demo.controller;

import com.sample.demo.dto.MemberDto;
import com.sample.demo.model.Member;
import com.sample.demo.repository.MemberRepository;
import com.sample.mvc.Account;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    public final MemberRepository memberRepository;

    public DemoController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/api")
    public ResponseEntity<Page<Member>> getPage(Pageable pageable) {
        final Page<Member> page = memberRepository.findAll(pageable);
        System.out.println(page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> members = memberRepository.findAll();
        members.forEach(member -> logger.info(member.toString()));
        return new ResponseEntity(members, HttpStatus.OK);
    }

    @PostMapping("/api/members")
    public ResponseEntity<String> createUser(@RequestBody MemberDto memberDto) {

        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return ResponseEntity.ok("resource update");
    }

    @GetMapping("/api/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberRepository.findById(id).orElse(new Member());
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return ResponseEntity.ok("resource delete");
    }
}
