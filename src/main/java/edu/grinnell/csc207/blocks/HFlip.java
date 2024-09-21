package edu.grinnell.csc207.blocks;

/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Kevin Tang
 */
public class HFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public HFlip(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Illegal Row Number");
    } //If Statement
    int currWidth = this.block.width();
    String org = this.block.row(i);
    StringBuilder rowAdj = new StringBuilder();
    for (int j = 0; j < currWidth; j++) {
      rowAdj.append(org.charAt(currWidth - 1 - j));
    }
    return rowAdj.toString();
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.block.width();   // STUB
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    int currHeight = this.block.height();
    for (int i = 0; i < currHeight; i++) {
      try {
        if (!this.block.row(i).equals(other.row(i))) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  } // eqv(AsciiBlock)
} // class HFlip
