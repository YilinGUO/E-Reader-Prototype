* All book pages are assumed with jpg format.
* The user input text field is currently set as 20. You can change it by 
changing MAX_TEXT_SIZE.

To update the books in the library, you have to modify all the TODO field: 
- the bookLongTitleArr (the complete title of the book)
- the bookTitleArr (the title of the book contained in the page file name)
- the bookMaxPageArr (the total page of each book)
- the lastTimePageArr (which is all 1's with length of the library size)
	
Also, there are two search functions to be implemented:
- private void authorSearch(String author)
- private void titleSearch(String title)