package fastcampus.innercircle.oss.methodviewer.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/speed")
    public String speed() throws InterruptedException {
        testService.speed();
        return "speed";
    }

    @GetMapping("/param")
    public String param() {
        testService.param("패캠");
        return "param";
    }

    @GetMapping("/return")
    public String returnValue() {
        Map<String, Object> result = testService.returnValue("패캠");
        return result.toString();
    }
}
