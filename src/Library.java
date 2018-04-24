/**
 * This class represents a Library object.
 *
 * Author: Daniel Litvak
 * Date: 8.4.2018
 */
public class Library
{
    private Book[] _lib;
    private int _noOfBooks;

    // Constants
    private final int MAX_BOOKS = 200;

    //Constructor

    /**
     *
     */
    public Library()
    {
        _lib = new Book[MAX_BOOKS];
        _noOfBooks = 0;
    }

    /**
     * @param b
     * @return
     */
    public boolean addBook(Book b)
    {
        if (_noOfBooks < MAX_BOOKS)
        {
            _lib[_noOfBooks] = new Book(b);
            _noOfBooks++;
            return true;
        }
        return false;
    }

    /**
     * @param b
     */
    public void removeBook(Book b)
    {
        for (int i = 0; i < _noOfBooks; i++)
        {
            // loop until the book is not the book that is needed to remove,
            while (_lib[i].equals(b))
            {
                // Remove the book
                _lib[i] = null;

                // Fix the hole
                fixHole(i);

                // Remove a book from the number of books
                _noOfBooks--;
            }
        }
    }

    public Book remove(String title)
    {
        // Search for the book to remove
        for (int i = 0; i < _noOfBooks; i++)
        {
            // If the book is found
            if (_lib[i].getTitle().equals(title))
            {
                // keep the book in a variable to return it later
                Book book=_lib[i];

                // Remove the book
                _lib[i] = null;

                // Fix the hole
                fixHole(i);

                // Remove a book from the number of books
                _noOfBooks--;

                return book;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public int howManyBooksBorrowed()
    {
        int counter = 0;
        for (int i = 0; i < _noOfBooks; i++)
        {
            if (_lib[i].getBorrowed())
            {
                // If the book is borrowed add to the counter
                counter++;
            }
        }
        return counter;
    }

    /**
     * @param name
     * @return
     */
    public int howManyBooksBorrowedToStudent(String name)
    {
        int counter = 0;
        for (int i = 0; i < _noOfBooks; i++)
        {
            if (_lib[i].getBorrowed())
            {
                // If the book is borrowed and the student name is the same, add to the counter
                if (_lib[i].getStudentName().equals(name))
                    counter++;
            }
        }
        return counter;
    }

    /**
     * @return
     */
    public String mostPopularAuthor()
    {
        if (_noOfBooks == 0)
        {
            // If there are no books return null
            return null;
        }

        // max index is the index that points on the first book that its author has the most books
        int maxIndex = 0;
        // max count is the number of times the most popular author appears
        int maxCount = 0;
        int counter;

        for (int i = 0; i < _noOfBooks - 1; i++)
        {
            counter = 0;
            String author = _lib[i].getAuthor();
            // Count from index i to index j the number of times the author appears
            for (int j = i + 1; j < _noOfBooks; j++)
            {
                if (_lib[j].getAuthor().equals(author))
                {
                    counter++;
                }
            }
            // If a new maximum was found replace the old maximum and set the maximum index to the right place
            if (counter > maxCount)
            {
                maxCount = counter;
                maxIndex = i;
            }
        }

        return _lib[maxIndex].getAuthor();
    }

    /**
     * @return
     */
    public Book oldestBook()
    {
        if (_noOfBooks == 0)
        {
            // If there are no books return null
            return null;
        }

        int oldestIndex = 0;
        int oldestDate = _lib[oldestIndex].getYear();

        for (int i = 1; i < _noOfBooks; i++)
        {
            // If found a book older than the current oldest book replace the oldest book index and the oldest date
            if (_lib[i].getYear() < oldestDate)
            {
                oldestIndex = i;
                oldestDate = _lib[i].getYear();
            }
        }
        return _lib[oldestIndex];
    }

    /**
     * @return
     */
    public String toString()
    {
        String s = "The books in the library are:\n";

        for (int i = 0; i < _noOfBooks; i++)
        {
            s += _lib[i].toString() + "\n";
        }
        return s;
    }

    // Fix a hole in the array in the specific index
    private void fixHole(int holeIndex)
    {
        // If the hole is not in the last place it needs to be fixed
        if (holeIndex != _noOfBooks - 1)
        {
            // Put the last book in the hole
            _lib[holeIndex] = _lib[_noOfBooks - 1];
            // remove the last reference
            _lib[_noOfBooks - 1] = null;
        }
    }
}
