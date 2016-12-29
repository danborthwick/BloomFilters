package cc.smartcasual.filter;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class RandomHashesTest {

    @Test
    public void hashesAreLessThanBitFieldSize() throws Exception {
        final int BITFIELD_SIZE = 33;

        for (int i=0; i < 10; i++) {
            Stream<Integer> hashes = new RandomHashes.Builder().build(i, BITFIELD_SIZE).stream().limit(6);
            hashes.forEach(hash -> assertThat(hash, lessThan(BITFIELD_SIZE)));
        }
    }
}