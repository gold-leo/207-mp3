package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Leo Goldman
 * @author Your Name Here
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBlock[])

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
      throw new Exception("Illegal row number " + i);
    } // exception

    AsciiBlock b = null;
    for (AsciiBlock block : blocks) {
      i -= block.height();
      if (i < 0) {
        b = block;
        break;
      } // if
    } // loop through blocks
    if (b == null) {
      throw new Exception("No blocks");
    } // if no blocks exist
    int row = b.height() + i;
    int spaces = this.width() - b.width();

    String sp = new String(" ");
    String str = new String();
    switch (align) {
      case RIGHT:
        str = new String(sp.repeat(spaces) + b.row(row));
        break;
      case LEFT:
        str = new String(b.row(row) + sp.repeat(spaces));
        break;
      case CENTER:
        int h_s = spaces / 2; // num of spaces on each side
        str = new String(sp.repeat(h_s) + b.row(row) + sp.repeat(h_s + (spaces % 2)));
        break;
      default:
        break;
    } // design string depending on alignment

    return str;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int h = 0;
    for (AsciiBlock block : blocks) {
      h += block.height();
    } // loop over every block
    return h;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int w = 0;
    for (AsciiBlock block : blocks) {
      int bw = block.width();
      if (w < bw) {
        w = bw;
      } // if width is larger
    } // loop over every block
    return w;
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
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
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
  public boolean eqv(VComp other) {
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
