package L4_Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

public class TriangleAreaTest {

    private static Logger logger = LoggerFactory.getLogger(TriangleAreaTest.class);


    @Test
    @DisplayName("Assert the certain Area")
    void certainArea() throws NegaiveSidesException {
        logger.debug("Test-run info:");
        double result = TriangleArea.countArea(2, 2, 2);
        Assertions.assertEquals(1.7320508075688772, result);
    }

    @Test
    @DisplayName("Check positive sides")
    void testPositive() throws NegaiveSidesException {
        logger.debug("Test-run info:");
        assertThat(TriangleArea.countArea(2,2,2)).isPositive();
    }

    @Test
    @DisplayName("Check negative sides (Fail example)")
    void testNegative() throws NegaiveSidesException {
        logger.debug("Test-run info:");
        assertThat(TriangleArea.countArea(-1,-2,-4)).isNegative();
    }

    @Test
    @DisplayName("Check the exception")
    void testIsException() {
        logger.debug("Test-run info:");
        assertThatExceptionOfType(NegaiveSidesException.class).isThrownBy(()
                -> TriangleArea.countArea(-1, -1, -1));
    }
}
