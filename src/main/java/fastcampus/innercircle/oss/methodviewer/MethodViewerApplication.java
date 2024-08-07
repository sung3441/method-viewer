package fastcampus.innercircle.oss.methodviewer;

import fastcampus.innercircle.oss.methodviewer.annotations.EnableGlobalMethodViewer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableGlobalMethodViewer
public class MethodViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MethodViewerApplication.class, args);
	}

}
