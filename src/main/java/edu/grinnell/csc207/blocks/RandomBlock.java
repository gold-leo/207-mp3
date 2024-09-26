package edu.grinnell.csc207.blocks;

import java.util.Random;

/**
 * RandomBlock takes an AsciiBlock and returns a randomized version of it.
 */
public class RandomBlock implements AsciiBlock {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * The random number generator.
   */
  private final Random random;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * @param original original (not randomized) block
   */
  public RandomBlock(AsciiBlock original) {
    this.block = original;
    this.random = new Random();
  } //RandomBlock

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * @param i row number
   * @return the row of the block
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Row index out of bounds.");
    } //if statement

    String rowInit = this.block.row(i);
    return new String(randomArray(rowInit.toCharArray()));
  } //row


  /**
   * @param arr character array
   * @return randomized character array
   */
  private char[] randomArray(char[] arr) {
    for (int j = arr.length - 1; j > 0; j--) {
      int index = random.nextInt(j + 1);
      char temp = arr[j];
      arr[j] = arr[index];
      arr[index] = temp;
    } //for loop
    return arr;
  } //randomArray

  /**
   * @return The number of rows in the block.
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * @return The number of columns in the block.
   */
  public int width() {
    return this.block.width();
  } // width()

  /**
   * @param other
   *   The RandomBlock to compare to this RandomBlock.
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    if (!(other instanceof RandomBlock)) {
      return false;
    } //if statement
    return true;
  } //random eqv

} // class RandomBlock
