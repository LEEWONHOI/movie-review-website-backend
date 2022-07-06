package hello.moivereview.web.controller.member.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberInfoDto {

    private String name;
    private String email;

}
