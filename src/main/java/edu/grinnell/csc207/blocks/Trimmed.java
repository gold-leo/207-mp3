package edu.grinnell.csc207.blocks;

/**
 * A trimmed ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Kevin Tang
 * @author Leo Goldman
 */
public class Trimmed implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * Which part of the block do we keep horizontally?
   */
  HAlignment halign;

  /**
   * Which part of the block do we keep vertically?
   */
  VAlignment valign;

  /**
   * How much of the block do we keep horizontally?
   */
  int width;

  /**
   * How much of the block do we keep vertically?
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
   * @param horiz
   *   How the trimmed block is horizontally aligned on the original.
   * @param vert
   *   How the trimmed block is vertically aligned on the original.
   * @param trimmedWidth
   *   The width of the trimmed block.
   * @param trimmedHeight
   *   The height of the trimmed block.
   */
  public Trimmed(AsciiBlock original, HAlignment horiz, VAlignment vert,
      int trimmedWidth, int trimmedHeight) {
    this.block = original;
    this.halign = horiz;
    this.valign = vert;
    this.width = trimmedWidth;
    this.height = trimmedHeight;
  } // Trimmed(AsciiBlock, HAlignment, VAlignment, int, int)

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
    } //if statement

    int rowInit = wAdj(this.width);
    int colInit = hAdj(this.height);

    String rowAdj = this.block.row(rowInit + i);
    return rowAdj.substring(colInit, colInit + this.width);
  } // row(int)

  private int wAdj(int wid) {
    int standard = this.block.width();
    switch (this.valign) {
      case TOP:
        return 0;
      case BOTTOM:
        return standard - wid;
      case CENTER:
        return (standard - wid) / 2;
      default:
        return 0;
    } //switch
  } //wAdj


  private int hAdj(int heigh) {
    int standard = this.block.height();
    switch (this.valign) {
      case TOP:
        return 0;
      case BOTTOM:
        return standard - heigh;
      case CENTER:
        return (standard - heigh) / 2;
      default:
        return 0;
    } //switch
  } //hAdj


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
    return ((other instanceof Trimmed) && (this.eqv((Trimmed) other)));
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
  public boolean eqv(Trimmed other) {
    return this.width == other.width && this.height == other.height
        && this.halign == other.halign && this.valign == other.valign
        && this.block.eqv(other.block);
  } // eqv(Grid)
} // class Trimmed
