package cc.smartcasual;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class BloomFilterTest
{
    static BloomFilter filter;

    @BeforeClass
    public static void loadDictionary() throws Exception {
        DictionaryLoader loader = new DictionaryLoader("words.txt");
        filter = new BloomFilter();
        loader.forEachWord(word -> filter.add(word));
    }

    @Test
    public void filterWasCreated()
    {
        assertThat(filter, notNullValue());
    }
}