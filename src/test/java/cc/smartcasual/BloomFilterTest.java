package cc.smartcasual;

import cc.smartcasual.filter.BloomFilter;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class BloomFilterTest
{
    static BloomFilter filter;

    @BeforeClass
    public static void loadDictionary() throws Exception {
        filter = DictionaryLoader.loadEnglish().makeFilter();
    }

    @Test
    public void filterWasCreated() {
        assertThat(filter, notNullValue());
    }

    @Test
    public void filterHasManyBitsSet() throws Exception {
        assertThat(filter.numberOfBitsSet(), greaterThan(1000));
    }

    @Test
    public void unknownWordIsFiltered() throws Exception {
        //TODO: Statistically
        assertThat(filter.mayContain("notaword"), is(false));
    }

    @Test
    public void realWordIsNotFiltered() {
        assertThat(filter.mayContain("dishexecontahedroid"), is(true));
    }
}