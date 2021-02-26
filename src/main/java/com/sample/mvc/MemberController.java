package com.sample.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final AccountRepository accountRepository;

    @GetMapping("/")
    public ResponseEntity<Page<Account>> getPage(Pageable pageable) {
        final Page<Account> page = accountRepository.findAll(pageable);

        final Page<Account> all = accountRepository.findAll(PageRequest.of(1, 20));


        return ResponseEntity.ok(page);
    }

    @GetMapping("/members")
    public Account getMembers() {
        Account account = new Account();
        account.setId(1L);
        account.setName("yhh");

        return account;
    }

    @GetMapping("/api/members")
    public ResponseEntity<SimpleResponse> getMemberAPI() {
        Account account = new Account();
        account.setId(1L);
        account.setName("yhh");

        //저장하는 로직

        return ResponseEntity.ok().body(new SimpleResponse(true, "success"));

    }
}
