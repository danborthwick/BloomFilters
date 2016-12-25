package cc.smartcasual;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

\import static org.junit.Assert.assertThat;

public class FilterSizeTest {

    @Test
    public void hashSetSizeExceedsBloomFilterSize() throws Exception {
        SetFilter<String> naiiveFilter = new HashSetFilter<>();
        DictionaryLoader.loadEnglish().forEachWord(word -> naiiveFilter.add(word));

        SetFilter<String> bloomFilter = new BloomFilter<>();
        DictionaryLoader.loadEnglish().forEachWord(word -> bloomFilter.add(word));

        assertThat(sizeOf(bloomFilter), lessThan(sizeOf(naiiveFilter)));
    }

    private int sizeOf(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        return baos.size();
    }
}