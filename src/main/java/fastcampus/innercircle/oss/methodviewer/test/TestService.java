package fastcampus.innercircle.oss.methodviewer.test;

import fastcampus.innercircle.oss.methodviewer.annotations.ParameterViewer;
import fastcampus.innercircle.oss.methodviewer.annotations.ReturnValueViewer;
import fastcampus.innercircle.oss.methodviewer.annotations.SpeedViewer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {
    @SpeedViewer
    public void speed() throws InterruptedException {
        Thread.sleep(1_000);
    }

    @ReturnValueViewer
    public Map<String, Object> returnValue(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", 100);
        return map;
    }

    @ParameterViewer
    public void param(String name) {
    }
}
