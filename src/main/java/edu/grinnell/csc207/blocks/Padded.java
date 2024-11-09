package edu.grinnell.csc207.blocks;

/**
 * A padded ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Kevin Tang
 * @author Leo Goldman
 */
public class Padded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * The character used for padding.
   */
  String pad;

  /**
   * How is the original block horizontally aligned within the padding?
   */
  HAlignment halign;

  /**
   * How is the original block vertically aligned within the padding?
   */
  VAlignment valign;

  /**
   * How wide is the padded block?
   */
  int width;

  /**
   * How tall is the padded block.
   */
  int height;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   * @param ch
   *   The character we use for padding.
   * @param horiz
   *   How the original block is horizontally aligned within the padding.
   * @param vert
   *   How the original block is vertically aligned within the padding.
   * @param paddedWidth
   *   The width of the padded block.
   * @param paddedHeight
   *   The height of the padded block.
   */
  public Padded(AsciiBlock original, char ch, HAlignment horiz,
      VAlignment vert, int paddedWidth, int paddedHeight) {
    this.block = original;
    this.pad = new String(new char[] {ch});
    this.halign = horiz;
    this.valign = vert;
    this.width = paddedWidth;
    this.height = paddedHeight;
  } // Padded(AsciiBlock, char, HAlignment, VAlignment, int, int)

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
      throw new Exception("Illegal row number " + i);
    } // exception
    String blockRow = new String(pad.repeat(this.width()));
    int blockIndex = i;

    int h = this.height() - block.height();
    if (h < 0) {
      throw new Exception("Height of inner block larger than height of padded block");
    } // if
    switch (valign) {
      case TOP:
        if (i > block.height()) {
          return blockRow;
        } // if
        blockIndex = i;
        break;
      case BOTTOM:
        if (i < h) {
          return blockRow;
        } // if
        blockIndex = i - h;
        break;
      case CENTER:
        int c = h / 2;
        if (i >= (this.height() - c - (h % 2)) || i < c) {
          return blockRow;
        } // if
        blockIndex = i - c;
        break;
      default:
        break;
    } // switch

    int w = this.width() - block.width();
    if (w < 0) {
      throw new Exception("Width of inner block larger than width of padded block (" + w + ")");
    } // if
    switch (halign) {
      case RIGHT:
        blockRow = new String(pad.repeat(w) + block.row(blockIndex));
        break;
      case LEFT:
        blockRow = new String(block.row(blockIndex) + pad.repeat(w));
        break;
      case CENTER:
        int hs = w / 2; // num of spaces on each side
        blockRow = new String(pad.repeat(hs) + block.row(blockIndex) + pad.repeat(hs + (w % 2)));
        break;
      default:
        break;
    } // switch
    return blockRow;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.width;
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
    return ((other instanceof Padded) && (this.eqv((Padded) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another padded is structurally equivalent to this padded.
   *
   * @param other
   *   The padded to compare to this padded.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(Padded other) {
    return (this.height() == other.height()) && (this.width() == other.width())
        && (this.block.eqv(other.block)) && (this.halign == other.halign)
        && (this.valign == other.valign) && (this.pad == other.pad);
  } // eqv ()
} // class Padded
