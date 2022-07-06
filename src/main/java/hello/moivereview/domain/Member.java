package hello.moivereview.domain;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  // TODO EAGER 변경할것
    @JoinColumn(foreignKey = @ForeignKey(name = "member_id"))
    private Set<Authority> authorities;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private City city;

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    // TODO 양방향 연결이 필요한지 프로젝트 진행하며 체크할것
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    private boolean enabled;


    public void addCreateTime(LocalDateTime createTime) {
        this.created = createTime;
    }

    public void addUpdateTime(LocalDateTime updateTiem) {
        this.updated = updateTiem;
    }

    public void registerAuthority(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
