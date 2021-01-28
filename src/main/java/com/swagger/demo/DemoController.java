package com.swagger.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "find all members")
    @ApiResponse(responseCode = "200", description = "get all members",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Member.class))})
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> members = memberRepository.findAll();
        members.forEach(member -> logger.info(member.toString()));
        return new ResponseEntity(members, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<String> createUser(@RequestBody MemberDto memberDto) {
        logger.info(memberDto.getUserId());
        logger.info(memberDto.getPassword());

        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return ResponseEntity.ok("resource update");
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NullPointerException());
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return ResponseEntity.ok("resource delete");
    }
}
