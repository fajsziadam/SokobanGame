package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SokobanStateTest {

    private SokobanState sokobanState;

    @BeforeEach
    public void setUp() throws Exception {
        Map gameMapGrid = new Map();
        Player player = new Player(1, 1);
        sokobanState = new SokobanState(gameMapGrid, player);
    }

    @Test
    public void testIsLegalMove() {
        assertTrue(sokobanState.isLegalMove(Directions.RIGHT));
        assertTrue(sokobanState.isLegalMove(Directions.DOWN));
        assertFalse(sokobanState.isLegalMove(Directions.UP));
        assertFalse(sokobanState.isLegalMove(Directions.LEFT));
    }

    @Test
    public void testCanPushBall() {
        sokobanState.getPlayer().setX(3);
        sokobanState.getPlayer().setY(1);

        assertFalse(sokobanState.canPushBall(Directions.UP));
        assertFalse(sokobanState.canPushBall(Directions.RIGHT));
        assertTrue(sokobanState.canPushBall(Directions.DOWN));
        assertFalse(sokobanState.canPushBall(Directions.LEFT));
    }

    @Test
    public void testPushBall() {
        sokobanState.getPlayer().setX(3);
        sokobanState.getPlayer().setY(1);

        assertTrue(sokobanState.canPushBall(Directions.DOWN));
        sokobanState.makeMove(Directions.DOWN);
        assertTrue(sokobanState.getMap().isBall(3, 3));

        assertFalse(sokobanState.canPushBall(Directions.UP));
        sokobanState.makeMove(Directions.DOWN);
        assertFalse(sokobanState.getMap().isBall(3, 2));
    }

    @Test
    public void testMakeMove() {
        assertEquals(1, sokobanState.getPlayer().getX());
        assertEquals(1, sokobanState.getPlayer().getY());

        sokobanState.makeMove(Directions.RIGHT);
        assertEquals(2, sokobanState.getPlayer().getX());
        assertEquals(1, sokobanState.getPlayer().getY());

        sokobanState.makeMove(Directions.RIGHT);
        assertEquals(3, sokobanState.getPlayer().getX());
        assertEquals(1, sokobanState.getPlayer().getY());

        sokobanState.makeMove(Directions.DOWN);
        assertEquals(3, sokobanState.getPlayer().getX());
        assertEquals(2, sokobanState.getPlayer().getY());
    }

    @Test
    void testIsSolved() {
        assertFalse(sokobanState.isSolved());
    }

}
