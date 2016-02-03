/**Purpose: This class serves as a GUI frame of an E-reader where users could 
 * select the book they have purchased and the photos of each page at a time
 * is displayed, and the users could "read" by flipping over the pages. The 
 * app would support storing as many books as the reader want.
 * 
 */
package eecs285.proj4.guoyilin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 * @author Yilin
 *
 */
public class BookBrowserFrame extends JFrame
{
  // max size of the user-input text field
  public static final int MAX_TEXT_SIZE = 20;
  private JComboBox<String> bookTitleComb = new JComboBox<String>();
  private JLabel authorLabel;
  private JButton authorSearchButton;
  private JTextField authorField;
  private JLabel titleLabel;
  private JTextField titleField;
  private JButton titleSearchButton;
  private JButton leftButton;
  private JButton leftMostButton;
  private JLabel imgLabel;
  private JButton rightButton;
  private JButton rightMostButton;
  private URL imgURL = getClass().getResource("/images/NoBookSelected.png");
  private String bookLongTitleArr[] = { "The Berenstain Bears Thanksgiving",
      "There Was an Old Lady Who Swallowed a Chick",
      "Pete the Cat and His Magic Sunglasses" };//TODO
  private String bookTitleArr[] = { "berenstain", "oldLady", "pete" };//TODO
  private int bookMaxPageArr[] = { 4, 4, 4 };//TODO
  private int lastTimePageArr[] = { 1, 1, 1 };//TODO
  private int currentBookIndex = -1;
  private BookBrowserListener myListener = new BookBrowserListener();
  private ComboListener myCombListener = new ComboListener();
  private JLabel currentPageInfo = new JLabel(" ");

  /**
   * The constructor "draws" the layout and links the buttons with pre-defined
   * listeners. The layout consists of the topPan, centerPan, and bottomPan
   * in a border layout.
   */
  public BookBrowserFrame(String inTitle)
  {
    super(inTitle);
    
    // The topPan just consists of the comboBox.
    JPanel topPan = new JPanel(new FlowLayout());
    
    // The centerPan consists of the autorPan and the titlePan.
    JPanel centerPan = new JPanel(new GridLayout(2, 1));
    JPanel authorPan = new JPanel(new FlowLayout());
    JPanel titlePan = new JPanel(new FlowLayout());
    
    // The bottomPan consists of the backPan, pageBox, and forwardPan
    // in a flowLayout.
    JPanel bottomPan = new JPanel(new FlowLayout());
    Box pageBox = Box.createVerticalBox();
    JPanel backPan = new JPanel(new GridLayout(2, 1));
    JPanel forwardPan = new JPanel(new GridLayout(2, 1));
    
    // configures the bookTitleComb comboBox.
    bookTitleComb.addItem("No Book Selected");
    for (int counter = 0; counter < bookTitleArr.length; counter++)
    {
      bookTitleComb.addItem(bookLongTitleArr[counter]);
    }
    ((JLabel) bookTitleComb.getRenderer())
        .setHorizontalAlignment(SwingConstants.CENTER);
    topPan.add(bookTitleComb);

    // configures the authorPan and the titlePan
    authorLabel = new JLabel("Author", SwingConstants.RIGHT);
    authorField = new JTextField(MAX_TEXT_SIZE);
    authorSearchButton = new JButton("search");
    authorPan.add(authorLabel);
    authorPan.add(authorField);
    authorPan.add(authorSearchButton);
    titleLabel = new JLabel("     Title", SwingConstants.RIGHT);
    titleField = new JTextField(MAX_TEXT_SIZE);
    titleSearchButton = new JButton("search");
    titlePan.add(titleLabel);
    titlePan.add(titleField);
    titlePan.add(titleSearchButton);

    // configures the backPanel
    leftButton = new JButton("<");
    leftMostButton = new JButton("<<<");
    backPan.add(leftButton);
    backPan.add(leftMostButton);
    // configures the pageBox
    imgLabel = new JLabel(new ImageIcon(imgURL, "No Book"));
    imgLabel.setAlignmentX(CENTER_ALIGNMENT);
    pageBox.add(imgLabel);
    pageBox.add(Box.createVerticalGlue());
    pageBox.add(currentPageInfo);
    // configures the forwardPanel
    rightButton = new JButton(">");
    rightMostButton = new JButton(">>>");
    forwardPan.add(rightButton);
    forwardPan.add(rightMostButton);
    
    // setup the overall layout
    setLayout(new BorderLayout());
    add(topPan, BorderLayout.NORTH);

    centerPan.add(authorPan);
    centerPan.add(titlePan);

    bottomPan.add(backPan);
    bottomPan.add(pageBox);
    bottomPan.add(forwardPan);

    add(centerPan, BorderLayout.CENTER);
    add(bottomPan, BorderLayout.SOUTH);
    
    // Tooltips
    bookTitleComb.setToolTipText("select a book here");
    authorSearchButton.setToolTipText("search a book by author");
    titleSearchButton.setToolTipText("search a book by title");
    leftButton.setToolTipText("previous page");
    leftMostButton.setToolTipText("first page");
    rightButton.setToolTipText("next page");
    rightMostButton.setToolTipText("last page");
    checkingButtonEnabled();
    
    // link the buttons with listeners
    authorSearchButton.addActionListener(myListener);
    titleSearchButton.addActionListener(myListener);
    leftButton.addActionListener(myListener);
    leftMostButton.addActionListener(myListener);
    rightButton.addActionListener(myListener);
    rightMostButton.addActionListener(myListener);
    bookTitleComb.addActionListener(myCombListener);

  }// end of constructor

  
  
  /**
   * Search a book by entering author in the authorTextfield. This feature
   * has not been implemented yet
   */
  private void authorSearch(String author)
  {
    // TODO to be implemented
    JOptionPane.showMessageDialog(null,
        "Searching isn't implemented at this time!");
  }
  
  /**
   * Search a book by entering title in the titleTextfield. This feature
   * has not been implemented yet
   */
  private void titleSearch(String title)
  {
    // TODO to be implemented
    JOptionPane.showMessageDialog(null,
        "Searching isn't implemented at this time!");
  }

  
  /**
   * update the page displayed in the window
   */
  private void updatePage(String title, int page)
  {
    imgURL = getClass().getResource("/images/" + title + page + ".jpg");
    imgLabel.setIcon(new ImageIcon(imgURL, title));
  }

  /**
   * This listener listens to the button event, including the 
   * authorSearchButton, titleSearchButton, leftButton, rightButton,
   * leftMostButton, and the rightMostButton.
   */
  public class BookBrowserListener implements ActionListener
  {
   
    public void actionPerformed(ActionEvent actEv)
    {
      if (actEv.getSource() == authorSearchButton)
      {
        authorSearch(authorField.getText());
      } 
      else if (actEv.getSource() == titleSearchButton)
      {
        titleSearch(titleField.getText());
      }
      // the left button goes back one page. If on first page, wraps to 
      // last page
      else if (actEv.getSource() == leftButton)
      {
        if (lastTimePageArr[currentBookIndex] > 1)
        {
          lastTimePageArr[currentBookIndex]--;
        } 
        else
        {
          lastTimePageArr[currentBookIndex] = bookMaxPageArr[currentBookIndex];
        }
        updatePage(bookTitleArr[currentBookIndex],
            lastTimePageArr[currentBookIndex]);
        checkingButtonEnabled();
      }
      // wraps to the first page
      else if (actEv.getSource() == leftMostButton)
      {
        if (lastTimePageArr[currentBookIndex] != 1)
        {
          lastTimePageArr[currentBookIndex] = 1;
          updatePage(bookTitleArr[currentBookIndex],
              lastTimePageArr[currentBookIndex]);
          checkingButtonEnabled();
        }
      } 
      // the right button goes forward one page. If on last page, wraps to 
      // the first page
      else if (actEv.getSource() == rightButton)
      {
        if (lastTimePageArr[currentBookIndex] < 
            bookMaxPageArr[currentBookIndex])
          lastTimePageArr[currentBookIndex]++;
        else
          lastTimePageArr[currentBookIndex] = 1;
        updatePage(bookTitleArr[currentBookIndex],
            lastTimePageArr[currentBookIndex]);
        checkingButtonEnabled();
      } 
      // wraps to the last page
      else if (actEv.getSource() == rightMostButton)
      {
        if (lastTimePageArr[currentBookIndex] != 
            bookMaxPageArr[currentBookIndex])
        {
          lastTimePageArr[currentBookIndex] = bookMaxPageArr[currentBookIndex];
          updatePage(bookTitleArr[currentBookIndex],
              lastTimePageArr[currentBookIndex]);
          checkingButtonEnabled();
        }
      }
      updateCurrentPageInfo();
    }
  }

  /**
   * This listener listens to the comboBox event, and displays the 
   * corresponding page if certain source is selected.
   */  
  public class ComboListener implements ActionListener
  {
    public void actionPerformed(ActionEvent actEv)
    {
      JComboBox<String> comboRef = (JComboBox<String>) actEv.getSource();
      // linearly searches the bookTitleArr to see whether there is a match
      // between the user's choice and the book "library"
      for (int counter = 0; counter < bookTitleArr.length; counter++)
      {
        if (comboRef.getSelectedItem().equals(bookLongTitleArr[counter]))
        {
          updatePage(bookTitleArr[counter], lastTimePageArr[counter]);
          currentBookIndex = counter;
          updateCurrentPageInfo();
          checkingButtonEnabled();
          break;
        }
      }
      // if no book is selected, displays the "No Book" page
      if (comboRef.getSelectedItem().equals("No Book Selected"))
      {
        currentBookIndex = -1;
        imgURL = getClass().getResource("/images/NoBookSelected.png");
        imgLabel.setIcon(new ImageIcon(imgURL, "No Book"));
        updateCurrentPageInfo();
        checkingButtonEnabled();
      }
    }
  }
  
  /**
   * This function checks whether the button should be enabled. That is, if 
   * no book is selected, all of the left, leftMost, right, rightMost buttons 
   * are disabled; otherwise, if the user has selected a certain book, if 
   * the current page is the first page, the leftMost button is disabled; if 
   * the current page is the last page, the rightMost button is disabled.
   */ 
  private void checkingButtonEnabled()
  {
    // No book is selected
    if (currentBookIndex == -1)
    {
      leftButton.setEnabled(false);
      leftMostButton.setEnabled(false);
      rightButton.setEnabled(false);
      rightMostButton.setEnabled(false);
    } 
    else// if certain book is selected
    {
      leftButton.setEnabled(true);
      rightButton.setEnabled(true);
      if (lastTimePageArr[currentBookIndex] == 1)
      {
        leftMostButton.setEnabled(false);
        rightMostButton.setEnabled(true);
      } 
      else if (lastTimePageArr[currentBookIndex] == 
          bookMaxPageArr[currentBookIndex])
      {
        leftMostButton.setEnabled(true);
        rightMostButton.setEnabled(false);
      } 
      else
      {
        leftMostButton.setEnabled(true);
        rightMostButton.setEnabled(true);
      }
    }
  }
  
  /**
   * This function updates the currentPageInfo at the bottom of the layout 
   * once page has been changed.
   */
  private void updateCurrentPageInfo()
  {
    if (currentBookIndex == -1)
    {
      currentPageInfo.setText(" ");
    } else
    {
      currentPageInfo
          .setText(Integer.toString(lastTimePageArr[currentBookIndex]) + '/'
              + bookMaxPageArr[currentBookIndex]);
    }
  }
}