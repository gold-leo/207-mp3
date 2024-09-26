package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.RandomBlock;

/**
 * Tests for the RandomBlock class.
 */
public class TestOurBlock {

    // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Test randomization behaviors in RandomBlock.
   */
  @Test
  public void testRandomBlockBasicBehaviors() throws Exception {
    // Test 1: Two RandomBlocks with the same content should not be equivalent
    RandomBlock randomBlock1 = new RandomBlock(new Lines("abcde\nfghij\nklmno"));
    RandomBlock randomBlock2 = new RandomBlock(new Lines("abcde\nfghij\nklmno"));
    assertFalse(AsciiBlock.equal(randomBlock1, randomBlock2), "Randomized blocks should not be structurally equivalent");

    // Test 2: RandomBlock should be equivalent to itself
    assertTrue(randomBlock1.eqv(randomBlock2), "A RandomBlock should be equivalent to itself");

    // Test 3: Randomized row should be different from the original content
    assertFalse(randomBlock1.row(0).equals("abcde"), "RandomBlock row should be randomized");

    // Test 4: Empty RandomBlock should return an empty row
    RandomBlock emptyBlock = new RandomBlock(new Lines(""));
    assertEquals("", emptyBlock.row(0), "RandomBlock of an empty block should return an empty row");
  }

  /**
   * Test structural equivalence of RandomBlocks with different characteristics.
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

    // Test 7: RandomBlock should preserve height of the original block
    assertEquals(2, randomBlock1.height(), "RandomBlock should maintain the original block height");

    // Test 8: RandomBlock should preserve width of the original block
    assertEquals(5, randomBlock1.width(), "RandomBlock should maintain the original block width");
  }

  /**
   * Additional tests for edge cases and complex random block scenarios.
   */
  @Test
  public void testRandomBlockEdgeCases() throws Exception {
    // Test 9: RandomBlock row should still have the same length after randomization
    RandomBlock randomBlock = new RandomBlock(new Lines("hello\nworld"));
    assertEquals(5, randomBlock.row(0).length(), "RandomBlock row should have the same length after randomization");
  }
}