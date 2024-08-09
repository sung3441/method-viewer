package fastcampus.innercircle.oss.methodviewer.printers;

public class PrintSystemOut implements Print {
    PrintSystemOut() {
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
