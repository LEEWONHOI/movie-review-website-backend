package hello.moivereview;

import hello.moivereview.domain.*;
import hello.moivereview.service.MemberService;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;
        private final MemberService memberService;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        static class InitMovieData {
            private String movieTitle;
            private String movieYear;
            private String moviePoster;
        }

        public void dbInit() {

            ArrayList<InitMovieData> initMovieDataArrayList = new ArrayList<>();

            String[] movieTitles = {
                    "frozen1",
                    "frozen2",
                    "frozen3",

                    "test1",
                    "test2",
                    "test3",
                    "test4",
                    "test5",
                    "test6",
                    "test7",
                    "test8",
                    "test9",
                    "test10",

                    "jobs1",
                    "jobs2",
                    "jobs3",
                    "jobs4",
                    "jobs5",
                    "jobs6",
                    "jobs7",
                    "jobs8",
                    "jobs9",
                    "jobs10",

                    "like1",
                    "like2",
                    "like3",
                    "like4",
                    "like5",
                    "like6",
                    "like7",
                    "like8",
                    "like9",
                    "like10",
            };
            String[] movieYears = {
                    "2000",
                    "2001",
                    "2002",

                    "2000",
                    "2001",
                    "2002",
                    "2003",
                    "2004",
                    "2005",
                    "2006",
                    "2007",
                    "2008",
                    "2009",
                    "20010",

                    "2000",
                    "2001",
                    "2002",
                    "2003",
                    "2004",
                    "2005",
                    "2006",
                    "2007",
                    "2008",
                    "2009",
                    "20010",

                    "2000",
                    "2001",
                    "2002",
                    "2003",
                    "2004",
                    "2005",
                    "2006",
                    "2007",
                    "2008",
                    "2009",
                    "20010",
            };
            String[] moviePosters = {
                    "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg",
                    "https://m.media-amazon.com/images/M/MV5BMjA0YjYyZGMtN2U0Ni00YmY4LWJkZTItYTMyMjY3NGYyMTJkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg",
                    "https://m.media-amazon.com/images/M/MV5BMTc5MTg0ODgxMF5BMl5BanBnXkFtZTcwODEzOTYwMw@@._V1_SX300.jpg",

                    "https://m.media-amazon.com/images/I/61zO3dz93uL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91k8IK1a95L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81FQBVSc0sL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81nplIqRvZL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81rIst6El-L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/710I1jYYSyL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91CloiItBzL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91R5mCfwgeL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91M2dVrfAaL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/817nz49nFgL._AC_SX300_.jpg",


                    "https://m.media-amazon.com/images/I/81lOrFV0JGL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/71cYr9Jns0L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81tJvi4hy8L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91R1HTW0kUL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91cNweqDaIL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81x0Y9i9Q2L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91I4T4SVyFL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91Yls64WG4L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81qJ0LU38JL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81cPUoCbeiL._AC_SX300_.jpg",

                    "https://m.media-amazon.com/images/I/81G4e2aNjUL._SX300_.jpg",
                    "https://m.media-amazon.com/images/I/51dLzRDfdAL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81Z+qD3jSoL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81PlUabn3mL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/A1NPPYOLP6L._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/81UR7rIHpgL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/617TeVzVdRL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/91TvExQ1TFL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/913v0sAkiyL._AC_SX300_.jpg",
                    "https://m.media-amazon.com/images/I/61ZNhgqbc9L._AC_SX300_.jpg"

            };

            for (int i = 0; i < movieTitles.length; i++) {
                initMovieDataArrayList.add(
                        InitMovieData.builder()
                                .movieTitle(movieTitles[i])
                                .movieYear(movieYears[i])
                                .moviePoster(moviePosters[i])
                                .build()
                );
            }

            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .password(passwordEncoder.encode("123"))
                    .enabled(true)
                    .build();
            em.persist(member);
            memberService.addAuthority(member.getId(), Authority.ROLE_MEMBER);
            em.flush();

            Member manager = Member.builder()
                    .name("test1")
                    .email("test@test.com1")
                    .password(passwordEncoder.encode("123"))
                    .enabled(true)
                    .build();
            em.persist(manager);
            memberService.addAuthority(manager.getId(), Authority.ROLE_MANAGER);
            em.flush();


            for (int i = 0; i < initMovieDataArrayList.size(); i++) {

                InitMovieData initMovieData = initMovieDataArrayList.get(i);

                Movie movie = new Movie(
                        initMovieData.getMovieTitle(),
                        initMovieData.getMovieYear(),
                        Type.movie,
                        initMovieData.getMoviePoster(),
                        0,
                        "PG",
                        "22 Nov 2019",
                        "103 min",
                        "Animation, Adventure, Comedy",
                        "Chris Buck, Jennifer Lee",
                        "Jennifer Lee, Hans Christian Andersen, Chris Buck",
                        "Kristen Bell, Idina Menzel, Josh Gad",
                        "Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsa's powers in order to save their kingdom.",
                        "English",
                        "United States",
                        "Nominated for 1 Oscar. 17 wins & 90 nominations total",
                        "64",
                        "6.8",
                        "165,211",
                        "tt4520988",
                        "movie",
                        "22 Nov 2019",
                        "$477,373,578",
                        true
                );
                movie.registerMember(manager);
                em.persist(movie);
            }
            em.flush();
            em.clear();
        }
    }
}
