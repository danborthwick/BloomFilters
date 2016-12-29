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
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BloomFilterFalsePositiveRateTest {

    @Parameters(name = "False positive rate: {0}")
    public static Collection<Object[]> data() throws Exception {
        return Arrays.asList(new Object[][]{
                {0.1},
                {0.01},
                {0.001},
        });
    }

    @Parameter
    public double expectedRate;

    class Stats {
        int hits;
        int misses;
    }

    @Test
    public void falsePositiveRate() throws Exception {
        int wordCount = dictionary().count();

        BloomFilter filter = BloomFilterBuilder.forElementCount(wordCount).withFalsePositiveRate(expectedRate).build();
        dictionary().forEachWord(word -> filter.add(word));

        final Stats stats = new Stats();

        dictionary().forEachWord(word -> {
            if (filter.mayContain("qx" + word)) {
                stats.hits++;
            }
            else {
                stats.misses++;
            }
        });

        double actualRate = (double) stats.hits / (double) wordCount;
        assertThat(actualRate, closeTo(expectedRate, expectedRate * 0.1));

    }

    private DictionaryLoader dictionary() throws Exception {
        return DictionaryLoader.loadEnglish();
    }
}
