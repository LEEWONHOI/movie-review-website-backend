package hello.moivereview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Authority.class)
public class Authority implements GrantedAuthority {

    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_MEMBER = "ROLE_MEMBER";

    public static final Authority MANAGER_AUTHORITY = Authority.builder().authority(ROLE_MANAGER).build();
    public static final Authority MEMBER_AUTHORITY = Authority.builder().authority(ROLE_MEMBER).build();

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Id
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }

}
