package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

// import edu.grinnell.csc207.blocks.AsciiBlock;
// import edu.grinnell.csc207.blocks.Boxed;
// import edu.grinnell.csc207.blocks.Empty;
// import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
// import edu.grinnell.csc207.blocks.HComp;
// import edu.grinnell.csc207.blocks.HFlip;
// import edu.grinnell.csc207.blocks.Line;
//import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
//import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;
//import edu.grinnell.csc207.blocks.VComp;

/**
 * Sandbox for testing.
 */
public class Sandbox {
  /**
   * Main function.
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    Rect rect = new Rect('X', 4, 3);
    Padded paddedRect = new Padded(rect, '.', HAlignment.LEFT, VAlignment.TOP, 4, 3);

    Blocks.figure(pen, "rect test", rect);
    Blocks.figure(pen, "padded test", paddedRect);
    rect.narrower();
    Blocks.figure(pen, "rect test", rect);
    Blocks.figure(pen, "padded test", paddedRect);
  } // main
} // class
