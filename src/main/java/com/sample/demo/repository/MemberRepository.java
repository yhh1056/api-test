package com.sample.demo.repository;

import com.sample.demo.model.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yhh1056
 * @since 2021/01/28
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAll();

    Optional<Member> findById(Long id);

    void deleteById(Long id);

    Optional<Member> findByUsername(String username);
}
