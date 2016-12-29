package cc.smartcasual.filter;

import org.junit.Test;

import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.assertThat;

public class RandomHashesTest {

    @Test
    public void hashesAreLessThanBitFieldSize() throws Exception {
        final int BITFIELD_SIZE = 33;

        for (int i=0; i < 10; i++) {
            assertThat(new RandomHashes(i, 6, BITFIELD_SIZE), everyItem(lessThan(BITFIELD_SIZE)));
        }
    }

    @Test
    public void numberOfIterations() throws Exception {
        assertThat(new RandomHashes("xxx", 23, 42), iterableWithSize(23));
    }
}