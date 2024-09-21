package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Kevin Tang
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Illegal Row Number");
    } //If Statement
    
    StringBuilder rowAdj = new StringBuilder();
    for (int j = 0; j < this.blocks.length; j++) {
      int currHeight = this.blocks[j].height();
      int currWidth = this.blocks[j].width();
      int numAdj = HAdj(currHeight);
      if (i < numAdj || i >= numAdj + currHeight) {
        rowAdj.append(" ".repeat(currWidth));
      } else {
        rowAdj.append(this.blocks[j].row(i - numAdj));
      } //If Statement
    } //For Loop
    return rowAdj.toString();
  } // row(int)


  private int HAdj (int height) {
    int standard = this.height();
    switch (align) {
      case TOP:
        return 0;
      case BOTTOM:
        return standard - height;
      case CENTER:
        return (standard - height) / 2;
      default:
        return 0;
    }
  }

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      height = Math.max(height, this.blocks[i].height());
    }
    return height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      width += this.blocks[i].width();
    }
    return width;
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
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
  } // eqv(AsciiBlock)


  /**
   * Determine if another grid is structurally equivalent to this grid.
   *
   * @param other
   *   The grid to compare to this grid.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(HComp other) {
    if (!(this.align == other.align && this.blocks.length == other.blocks.length)) {
      return false;
    }
    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      }
    }
    return true;
  } // eqv(Grid)
} // class HComp
