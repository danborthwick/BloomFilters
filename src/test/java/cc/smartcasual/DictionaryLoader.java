package cc.smartcasual;

import cc.smartcasual.filter.BloomFilter;
import cc.smartcasual.filter.BloomFilterBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DictionaryLoader {

    private final Path path;

    public DictionaryLoader(String resourceName) throws URISyntaxException, IOException {
        path = Paths.get(ClassLoader.getSystemResource(resourceName).toURI());
    }

    public static DictionaryLoader loadEnglish()
    {
        try {
            return new DictionaryLoader("words.txt");
        }
        catch (Exception e) {
            return null;
        }
    }

    public void forEachWord(Consumer<? super String> action) throws IOException {
        Stream<String> lines = readLines();
        lines.forEach(action);
    }

    private Stream<String> readLines() throws IOException {
        return Files.lines(path);
    }

    public int count() throws IOException {
        return (int) readLines().count();
    }

    public BloomFilter<String> makeFilter() throws IOException {
        BloomFilter filter = BloomFilterBuilder.forElementCount(count()).build();
        forEachWord(word -> filter.add(word));
        return filter;
    }


}
