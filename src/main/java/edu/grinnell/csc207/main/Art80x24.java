package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Your Name Here
 * @author Your Name Here
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
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


    AsciiBlock wheel = new Rect('&', 4, 4);
    AsciiBlock spa = new Rect(' ', 52, 6);
    AsciiBlock stands = new HComp(VAlignment.CENTER, new AsciiBlock[] {wheel, spa, wheel});

    AsciiBlock five = new Line("/                                                \\");
    AsciiBlock four = new Line("/                                              \\");
    AsciiBlock three = new Line("/                                            \\");
    AsciiBlock two = new Line("/                                          \\");
    AsciiBlock one = new Line("/****************************************\\");

    AsciiBlock window = new VComp(HAlignment.CENTER, new AsciiBlock[]
      {one, two, three, four, five});



    AsciiBlock car = new VComp(HAlignment.CENTER, new AsciiBlock[] {window, front, b2, b, stands});
    AsciiBlock art = new Padded(car, ' ', HAlignment.CENTER, VAlignment.CENTER, 80, 24);
    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
