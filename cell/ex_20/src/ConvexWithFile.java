import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

class ConvexWithFile extends Convex implements AutoCloseable {
    private final PrintWriter _out;

    public ConvexWithFile(String fileName) throws IOException {
        super();
        Path parent = Path.of(fileName).getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        this._out = new PrintWriter(new FileWriter(fileName, true));
    }

    @Override
    public void add(R2Point p) {
        super.add(p);
        _out.printf(Locale.US, "%.6f %.6f%n", p.getX(), p.getY());
        _out.flush();
    }

    @Override
    public void close() {
        _out.close();
    }
}