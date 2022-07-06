package hello.moivereview.repository;

import hello.moivereview.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String userEmail);

    @Modifying(clearAutomatically = true)
    @Query("update Member set name=?2, updated=?3 where id=?1")
    void updateMemberName(Long member_id, String userName, LocalDateTime update);

    Optional<Member> findMemberByEmail(String memberEmail);

}
