package cc.smartcasual.filter;

import cc.smartcasual.DictionaryLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BloomFilterTest
{
    @Parameters(name = "{0} filter")
    public static Collection<Object[]> data() throws Exception {
        return Arrays.asList(new Object[][]{
                {"Simple", simpleFilter(), "a", "f", 5},
                {"English Dictionary", englishDictionaryFilter(), "dishexecontahedroid", "notaword", DictionaryLoader.loadEnglish().count()},
                {"Proper Nouns", properNounsFilter(), "Judith", "notaname", DictionaryLoader.loadProperNouns().count()},
        });
    }

    @Parameter
    public String testName;

    @Parameter(value = 1)
    public BloomFilter<String> filter;
    @Parameter(value = 2)
    public String wordInSet;
    @Parameter(value = 3)
    public String wordNotInSet;
    @Parameter(value = 4)
    public double entryCount;
    static BloomFilter<String> simpleFilter() {
        BloomFilter<String> filter = BloomFilterBuilder.forElementCount(8).build();
        filter.add(Arrays.asList("a", "b", "c", "d", "e" ));
        return filter;
    }

    static BloomFilter<String> englishDictionaryFilter() throws Exception {
        return DictionaryLoader.loadEnglish().makeFilter();
    }

    private static BloomFilter<String> properNounsFilter() throws Exception {
        return DictionaryLoader.loadProperNouns().makeFilter();
    }

    @Test
    public void filterWasCreated() {
        assertThat(filter, notNullValue());
    }

    @Test
    public void filterHasBitsSet() throws Exception {
        assertThat(filter.numberOfBitsSet(), greaterThan(5));
    }

    @Test
    public void wordNotInSetIsFiltered() throws Exception {
        assertThat(filter.mayContain(wordNotInSet), is(false));
    }

    @Test
    public void wordInSetIsNotFiltered() {
        assertThat(filter.mayContain(wordInSet), is(true));
    }

    @Test
    public void estimatedEntryCount() {
        assertThat((double) filter.estimatedCount(), closeTo(entryCount, entryCount * 0.01));
    }
}