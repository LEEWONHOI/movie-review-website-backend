package hello.moivereview.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/api/hello")
    public String hello() {
        return "helloTest";
    }

    @GetMapping("/api/test")
    public String connetTest() {
        return "vue/index";
    }

}
