package hello.moivereview.service;

import hello.moivereview.domain.Authority;
import hello.moivereview.domain.Member;
import hello.moivereview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member save(Member member) {
        if (member.getId() == null) {
            member.addCreateTime(LocalDateTime.now());
        }
        member.addUpdateTime(LocalDateTime.now());
        return memberRepository.save(member);
    }

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Page<Member> memberList(int pageNum, int size) {
        return memberRepository.findAll(PageRequest.of(pageNum - 1, size));
    }

    public void addAuthority(Long memberId, String authority) {
        memberRepository.findById(memberId).ifPresent(member -> {
            Authority newRole = new Authority(member.getId(), authority);
            if (member.getAuthorities() == null) {
                HashSet<Authority> authorities = new HashSet<>();
                authorities.add(newRole);
                member.registerAuthority(authorities);
                save(member);
            } else if (!member.getAuthorities().contains(newRole)) {
                HashSet<Authority> authorities = new HashSet<>();
                authorities.addAll(member.getAuthorities());
                authorities.add(newRole);
                member.registerAuthority(authorities);
                save(member);
            }
        });
    }

    public void removeAuthority(Long memberId, String authority) {
        memberRepository.findById(memberId).ifPresent(member -> {
            if (member.getAuthorities() == null) return;

            Authority targetRole = new Authority(member.getId(), authority);
            if (member.getAuthorities().contains(targetRole)) {
                member.registerAuthority(
                        member.getAuthorities().stream().filter(auth -> !auth.equals(targetRole))
                                .collect(Collectors.toSet())
                );
                save(member);
            }
        });
    }

    public void updateMemberName(Long memberId, String memberName) {
        memberRepository.updateMemberName(memberId, memberName, LocalDateTime.now());
    }




















}
