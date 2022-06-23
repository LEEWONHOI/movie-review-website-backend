package hello.moivereview.web.controller.member.dto;

import hello.moivereview.domain.City;
import hello.moivereview.domain.Gender;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberSignDto {

    private String email;
    private String password;
    private String rePassword;
    private String name;
    private Integer age;
    private Gender gender;
    private City city;

}
