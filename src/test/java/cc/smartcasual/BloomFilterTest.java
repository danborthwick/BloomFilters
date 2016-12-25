package cc.smartcasual;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class BloomFilterTest
{
    static SetFilter filter;

    @BeforeClass
    public static void loadDictionary() throws Exception {
        DictionaryLoader dictionary = DictionaryLoader.loadEnglish();
        filter = BloomFilterBuilder.forElementCount(dictionary.count()).build();
        dictionary.forEachWord(word -> filter.add(word));
    }

    @Test
    public void filterWasCreated() {
        assertThat(filter, notNullValue());
    }

    @Test
    public void fakeWordIsFiltered() throws Exception {
        assertThat(filter.mayContain("thisisnotaword"), is(false));
    }

    @Test
    public void realWordIsNotFiltered() {
        assertThat(filter.mayContain("dishexecontahedroid"), is(true));
    }
}