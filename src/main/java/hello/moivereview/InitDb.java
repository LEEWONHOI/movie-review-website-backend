package hello.moivereview;

import hello.moivereview.domain.*;
import hello.moivereview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;

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


        public void dbInit(){

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


            Movie movie1 = new Movie(
                    "Frozen II",
                    "2019",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg",
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

            Movie movie2 = new Movie(
                    "frozen2",
                    "2002",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMjA0YjYyZGMtN2U0Ni00YmY4LWJkZTItYTMyMjY3NGYyMTJkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg",
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

            Movie movie3 = new Movie(
                    "frozen3",
                    "2003",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTc5MTg0ODgxMF5BMl5BanBnXkFtZTcwODEzOTYwMw@@._V1_SX300.jpg",
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

            Movie movie4 = new Movie(
                    "frozen",
                    "2001",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg",
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

            Movie movie5 = new Movie(
                    "frozen2",
                    "2002",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMjA0YjYyZGMtN2U0Ni00YmY4LWJkZTItYTMyMjY3NGYyMTJkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg",
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

            Movie movie6 = new Movie(
                    "frozen3",
                    "2003",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTc5MTg0ODgxMF5BMl5BanBnXkFtZTcwODEzOTYwMw@@._V1_SX300.jpg",
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

            Movie movie7 = new Movie(
                    "frozen",
                    "2001",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg",
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

            Movie movie8 = new Movie(
                    "frozen2",
                    "2002",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMjA0YjYyZGMtN2U0Ni00YmY4LWJkZTItYTMyMjY3NGYyMTJkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg",
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

            Movie movie9 = new Movie(
                    "frozen3",
                    "2003",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTc5MTg0ODgxMF5BMl5BanBnXkFtZTcwODEzOTYwMw@@._V1_SX300.jpg",
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

            Movie movie10 = new Movie(
                    "frozen",
                    "2001",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg",
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

            Movie movie11 = new Movie(
                    "frozen2",
                    "2002",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMjA0YjYyZGMtN2U0Ni00YmY4LWJkZTItYTMyMjY3NGYyMTJkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg",
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

            Movie movie12 = new Movie(
                    "frozen3",
                    "2003",
                    Type.movie,
                    "https://m.media-amazon.com/images/M/MV5BMTc5MTg0ODgxMF5BMl5BanBnXkFtZTcwODEzOTYwMw@@._V1_SX300.jpg",
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


            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(movie1);
            movies.add(movie2);
            movies.add(movie3);
            movies.add(movie4);
            movies.add(movie5);
            movies.add(movie6);
            movies.add(movie7);
            movies.add(movie8);
            movies.add(movie9);
            movies.add(movie10);
            movies.add(movie11);
            movies.add(movie12);

            for (Movie movie : movies) {
                em.persist(movie);
            }

        }
    }
}
