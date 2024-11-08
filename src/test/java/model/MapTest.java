package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class MapTest {

    private Map map;

    @BeforeEach
    void setUp() {
        try {
            map = new Map();
        } catch (FileNotFoundException e) {
            fail("Exception thrown during setup: " + e.getMessage());
        }
    }

    @Test
    void testIsWall() {
        assertTrue(map.isWall(0, 0));
        assertTrue(map.isWall(0, 1));
        assertTrue(map.isWall(0, 2));
        assertFalse(map.isWall(1, 1));
        assertFalse(map.isWall(3, 3));
        assertFalse(map.isWall(7, 5));
        assertTrue(map.isWall(8, 5));
    }

    @Test
    void testIsBall() {
        assertTrue(map.isBall(2, 2));
        assertTrue(map.isBall(2, 3));
        assertTrue(map.isBall(3, 2));
        assertFalse(map.isBall(1, 1));
        assertFalse(map.isBall(7, 5));
    }

    @Test
    void testIsGoal() {
        assertTrue(map.isGoal(7, 3));
        assertTrue(map.isGoal(7, 4));
        assertTrue(map.isGoal(7, 5));
        assertFalse(map.isGoal(7, 6));
    }

    @Test
    void testIsTile() {
        assertFalse(map.isTile(1, 1));
        assertFalse(map.isTile(2, 2));
        assertTrue(map.isTile(3, 3));
        assertTrue(map.isTile(3, 4));
        assertTrue(map.isTile(6, 5));
        assertFalse(map.isTile(7, 5));
    }

    @Test
    void testIsPlayer() {
        assertFalse(map.isPlayer(0, 0));
        assertTrue(map.isPlayer(1, 1));
        assertFalse(map.isPlayer(1, 2));
        assertFalse(map.isPlayer(2, 2));
        assertFalse(map.isPlayer(7, 5));
    }

}
