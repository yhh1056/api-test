package com.sample.demo.service;

import com.sample.demo.model.Member;
import com.sample.demo.repository.MemberRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author yhh1056
 * @since 2021/02/05
 */

@Service
public class MemberService{

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
