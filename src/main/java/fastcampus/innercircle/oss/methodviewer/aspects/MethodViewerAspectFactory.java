package fastcampus.innercircle.oss.methodviewer.aspects;

import fastcampus.innercircle.oss.methodviewer.printers.Print;

public class MethodViewerAspectFactory {
    public static MethodViewerAspect create(String baseDir, Print print) {
        return new MethodViewerAspect(baseDir, print);
    }
}
