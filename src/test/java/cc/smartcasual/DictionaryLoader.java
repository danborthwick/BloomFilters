package cc.smartcasual;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DictionaryLoader {

    private final Stream<String> lines;

    public DictionaryLoader(String resourceName) throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource(resourceName).toURI());
        lines = Files.lines(path);
    }

    public void forEachWord(Consumer<? super String> action)
    {
        lines.forEach(action);
    }
}
