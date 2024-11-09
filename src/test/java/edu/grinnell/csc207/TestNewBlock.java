package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.RandomBlock;
import edu.grinnell.csc207.blocks.Rect;

/**
 * Tests for the RandomBlock class.
 * @author Leo Goldman
 * @author Kevin Tang
 */
public class TestNewBlock {

    // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Test randomization behaviors in RandomBlock.
   */
  @Test
  public void testRandomBlockBasicBehaviors() throws Exception {
    RandomBlock randomBlock1 = new RandomBlock(new Lines("abcde\nfghij\nklmno"));
    RandomBlock randomBlock2 = new RandomBlock(new Lines("abcde\nfghij\nklmno"));
    Line line1 = new Line("Hello");
    Rect rect1 = new Rect('R', 3, 2);

    // Test 1: Two RandomBlocks with the same content should not equal
    assertFalse(AsciiBlock.equal(randomBlock1, randomBlock2), "Randomized blocks should not be structurally equivalent");

    // Test 2: Test the eqv function of random block
    assertTrue(randomBlock1.eqv(randomBlock2), "A RandomBlock should be equivalent to itself");
    assertFalse(randomBlock1.eqv(line1), "A RandomBlock should be equivalent to itself");
    assertFalse(randomBlock1.eqv(rect1), "A RandomBlock should be equivalent to itself");

    // Test 3: Randomized row should be different from the original
    assertFalse(randomBlock1.row(0).equals("abcde"), "RandomBlock row should be randomized");

    // Test 4: Empty RandomBlock should return an empty row
    RandomBlock emptyBlock = new RandomBlock(new Lines(""));
    assertEquals("", emptyBlock.row(0), "RandomBlock of an empty block should return an empty row");
  }

  /**
   * Test structural equivalence of RandomBlocks.
   */
  @Test
  public void testRandomBlockStructuralEquivalence() throws Exception {
    RandomBlock randomBlock1 = new RandomBlock(new Lines("abcde\nfghij"));
    // Test 5: RandomBlock with different lengths should not be equivalent
    RandomBlock shortBlock = new RandomBlock(new Lines("a\nb\nc"));
    RandomBlock longBlock = new RandomBlock(new Lines("abcde\nfghij"));
    assertFalse(AsciiBlock.equal(shortBlock,longBlock), "Blocks of different lengths should not be structurally equivalent");

    // Test 6: Empty RandomBlock and non-empty RandomBlock should not be equivalent
    RandomBlock emptyBlock = new RandomBlock(new Lines(""));
    RandomBlock nonEmptyBlock = new RandomBlock(new Lines("abc"));
    assertFalse(AsciiBlock.equal(emptyBlock,nonEmptyBlock), "An empty RandomBlock should not be equivalent to a non-empty RandomBlock");

    // Test 7: RandomBlock should have the same height and width of the original block
    assertEquals(2, randomBlock1.height(), "RandomBlock should maintain the original block height");
    assertEquals(5, randomBlock1.width(), "RandomBlock should maintain the original block width");
  }

  /**
   * Additional tests for edge case 1
   */
  @Test
  public void testRandomBlockEdgeCaseRow() throws Exception {
    // Test 8: RandomBlock row should still have the same length after randomization
    RandomBlock randomBlock = new RandomBlock(new Lines("hello\nworld"));
    assertEquals(5, randomBlock.row(0).length(), "RandomBlock row should have the same length after randomization");
  }

  /**
   * Additional tests for edge case 2
   */
  @Test
  public void testRandomBlockEdgeCaseOneElement() throws Exception {
    // Test 9: RandomBlock with only one element shouldn't change after randomization
    RandomBlock randomBlock = new RandomBlock(new Lines("a"));
    assertEquals("a", randomBlock.row(0), "RandomBlock with only one element shouldn't change after randomization");
  }
}