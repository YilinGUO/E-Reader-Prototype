package eecs285.proj4.guoyilin;

import javax.swing.JFrame;

/**
 * This class demos how to use the bookBrowserFrame
 */
public class BookBrowerserDemo
{
  public static void main(String [] args)
  {
    BookBrowserFrame win;
    win = new BookBrowserFrame("Book Browser Prototype");
    win.pack();
    win.setVisible(true);
    win.setDefaultCloseOperation(
    JFrame.EXIT_ON_CLOSE);
  }
}