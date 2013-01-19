package ro.danix.first.model.domain.user;

import java.math.BigInteger;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.util.ReflectionTestUtils;
import ro.danix.first.model.domain.Baby;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category(FastRunningTests.class)
@Slf4j
public class BabyParentTest {

    @Test
    public void addBabyTest() {
        // given
        log.info("start addBabyTest");
        Baby baby = new Baby("Gigi", new DateTime());
        ReflectionTestUtils.setField(baby, "id", new BigInteger("1"));
        BabyParent instance = new BabyParent();
        // when
        instance.addBaby(baby);
        // then
        assertThat(instance.getBabiesIds(), hasItem(baby.getId()));
        assertThat(instance.getBabiesCount(), is(new Long(1)));
    }
}
