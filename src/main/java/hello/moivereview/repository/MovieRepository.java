package hello.moivereview.repository;

import hello.moivereview.domain.Member;
import hello.moivereview.domain.Movie;
import hello.moivereview.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findTop10ByTitleContainingIgnoreCase(String title);

    Page<Movie> findAllByTitleContainingIgnoreCaseAndTypeEqualsAndYearEquals(String title, Type type, String year, Pageable pageable);

    Page<Movie> findAllByTitleContainingIgnoreCaseAndTypeEquals(String title, Type type, Pageable pageable);

    @Query(value = "select m from Movie m where m.title =?1 and m.type =?2 and m.year =?3")
    Page<Movie> findSearchMovie(String title, Type type, String year, Pageable pageable);

    @Query("select count(m) from Movie m where m.member.id=?1")
    long countMovieByMember(Long memberId);

    Optional<Movie> findByTitle(String title);

//    @Query("select m from Member m where m.id=?1")
    List<Movie> findAllByMemberId(Long memberId);

//    @Query("select m from Member m where m.id=?1")
    Page<Movie> findAllByMemberId(Long memberId, Pageable pageable);

    List<Movie> findTop6ByTitleContainingIgnoreCase(String title);

}
