package com.sample;


import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        final List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        final Optional<Member> findMember = memberRepository.findById(id);

        if (findMember.isPresent()) {
            return new ResponseEntity<>(findMember.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(notFound(), HttpStatus.NOT_FOUND);
    }

    private ErrorObject notFound() {
        return new ErrorObject(HttpStatus.NOT_FOUND.toString(), "member not found");
    }

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberRepository.save(member));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        Optional<Member> findMember = memberRepository.findById(id);

        if (findMember.isPresent()) {
            memberRepository.deleteById(id);
            return ResponseEntity.ok("delete");
        }
        return ResponseEntity.notFound().build();
    }

}
