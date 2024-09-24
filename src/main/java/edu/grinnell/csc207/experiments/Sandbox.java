package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import javax.swing.Box;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
// import edu.grinnell.csc207.blocks.AsciiBlock;
// import edu.grinnell.csc207.blocks.Boxed;
// import edu.grinnell.csc207.blocks.Empty;
// import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
// import edu.grinnell.csc207.blocks.HComp;
// import edu.grinnell.csc207.blocks.HFlip;
// import edu.grinnell.csc207.blocks.Line;
//import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
//import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;
//import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VComp;

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

    AsciiBlock m = new Rect('#', 12, 2);
    AsciiBlock s = new Rect(' ', 10, 1);
    AsciiBlock light = new VComp(HAlignment.CENTER, new AsciiBlock[] {m, s, m});

    AsciiBlock g = new Rect('%', 30, 5);
    AsciiBlock grill = new Boxed(g);
    AsciiBlock sp = new Rect(' ', 3, 1);

    AsciiBlock f = new HComp(VAlignment.TOP, new AsciiBlock[] {light, sp, grill, sp, light});
    AsciiBlock front = new Boxed(f);

    AsciiBlock b = new Rect('&', 68, 1);
    AsciiBlock b2 = new Rect('*', 68, 1);
  

    AsciiBlock stand = new Rect('&', 4, 4);
    AsciiBlock spa = new Rect(' ', 52, 6);
    AsciiBlock stands = new HComp(VAlignment.CENTER, new AsciiBlock[] {stand, spa, stand});

    AsciiBlock one = new Line("/                      \\");
    AsciiBlock two = new Line("/********************\\");

    AsciiBlock handle = new VComp(HAlignment.CENTER, new AsciiBlock[] {two, one});



    AsciiBlock test = new VComp(HAlignment.CENTER, new AsciiBlock[] {handle, front, b2, b, stands});
    AsciiBlock.print(pen, test);
    pen.println(test.width() + " " + test.height());

  } // main
} // class
