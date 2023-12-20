package pl.bratek20.algorithms.common.array.d2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Array2DTest {

    @Test
    void shouldMapToArray2DOfOtherType() {
        // given
        var arr = new Array2D<>(2, 2, 0);

        // when
        var boolArr = arr.map(cell -> cell.getPoint().column() % 2 == 0);

        //then
        boolArr.getColumn(0).forEach(cell -> assertTrue(cell.getValue()));
    }
}