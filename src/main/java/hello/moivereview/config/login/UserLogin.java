package hello.moivereview.config.login;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserLogin {

    private String username;
    private String password;
    private String site;
    private boolean rememberme;

}
