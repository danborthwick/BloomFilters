package cc.smartcasual;

import cc.smartcasual.filter.BloomFilter;
import cc.smartcasual.filter.SetFilter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class FilterSizeTest {

    @Test
    public void hashSetSizeExceedsBloomFilterSize() throws Exception {
        DictionaryLoader dictionary = DictionaryLoader.loadEnglish();

        SetFilter<String> naiiveFilter = new HashSetFilter<>();
        dictionary.forEachWord(word -> naiiveFilter.add(word));

        BloomFilter<String> bloomFilter = dictionary.makeFilter();

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