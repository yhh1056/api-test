package com.swagger.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author yhh1056
 * @since 2021/02/05
 */
@Configuration
public class MemberRunner implements ApplicationRunner {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member1 = new Member("yhh", "1234", 27, "seoul");
        Member member2 = new Member( "osh", "osh123", 43, "vietnam");
        Member member3 = new Member("aaa", "12", 21, "seoul");
        Member member4 = new Member("bbb", "12345", 21, "seoul");
        Member member5 = new Member("ccc", "12453", 23, "seoul");
        Member member6 = new Member("ddd", "12564", 26, "seoul");
        Member member7 = new Member("eee", "1275", 31, "seoul");
        Member member8 = new Member("hhh", "12434", 26, "seoul");
        Member member9 = new Member("qqq", "12345", 75, "seoul");

        memberService.createAdmin(member1);
        memberService.createAdmin(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
        memberRepository.save(member6);
        memberRepository.save(member7);
        memberRepository.save(member8);
        memberRepository.save(member9);
    }
}
