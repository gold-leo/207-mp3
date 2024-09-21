package edu.grinnell.csc207.blocks;

import java.util.Random;

/**
 * RandomBlock takes an AsciiBlock and returns a randomized version of it.
 */
public class RandomBlock implements AsciiBlock {

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * The random number generator.
   */
  private final Random random;


  public RandomBlock(AsciiBlock original) {
    this.block = original;
    this.random = new Random();
  } //RandomBlock


  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Row index out of bounds.");
    }

    String rowInit = this.block.row(i);
    return new String(randomArray(rowInit.toCharArray()));
  } //row


  private char[] randomArray(char[] arr) {
    for (int j = arr.length - 1; j > 0; j--) {
      int index = random.nextInt(j + 1);
      char temp = arr[j];
      arr[j] = arr[index];
      arr[index] = temp;
    }
    return arr;
  }

  /**
   * Determine how many rows are in the block.
   *
   * @return The number of rows in the block.
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return The number of columns in the block.
   */
  public int width() {
    return this.block.width();
  } // width()

  /**
   * Determine if another grid is structurally equivalent to this grid.
   *
   * @param other
   *   The grid to compare to this grid.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof RandomBlock) && (this.eqv((RandomBlock) other)));
  } // eqv
} // class RandomBlock
