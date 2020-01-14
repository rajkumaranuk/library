package com.sky.library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void init() {
        bookService = new BookServiceImpl(new BookRepositoryStub());
    }

    @Test(expected = ValidationException.class)
    public void retrieveBookThrowsErrorForInvalidBookReference() throws BookNotFoundException {
        bookService.retrieveBook("1234");
    }

    @Test(expected = BookNotFoundException.class)
    public void retrieveBookThrowsErrorForNonExistentBook() throws BookNotFoundException {
        bookService.retrieveBook("BOOK-999");
    }

    @Test
    public void retrieveBookSuccessfully() throws BookNotFoundException {
        final Book book = bookService.retrieveBook("BOOK-GRUFF472");
        Assert.assertNotNull(book);
    }

    @Test(expected = ValidationException.class)
    public void getBookSummaryThrowsErrorForInvalidBookReference() throws BookNotFoundException {
        bookService.getBookSummary("1234");
    }

    @Test(expected = BookNotFoundException.class)
    public void getBookSummaryThrowsErrorForNonExistentBook() throws BookNotFoundException {
        bookService.getBookSummary("BOOK-999");
    }

    @Test
    public void getBookSummaryWithShortReview() throws BookNotFoundException {
        final String expectedSummary = "[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods.";
        final String bookSummary = bookService.getBookSummary("BOOK-GRUFF472");
        Assert.assertNotNull(bookSummary);
        Assert.assertEquals(expectedSummary, bookSummary);
    }

    @Test
    public void getBookSummaryWithLongReview() throws BookNotFoundException {
        final String expectedSummary = "[BOOK-POOH222] Winnie The Pooh - In this first volume, we meet all the friends...";
        final String bookSummary = bookService.getBookSummary("BOOK-POOH222");
        Assert.assertNotNull(bookSummary);
        Assert.assertEquals(expectedSummary, bookSummary);
    }

    @Test
    public void getBookSummaryWithLongReview2() throws BookNotFoundException {
        final String expectedSummary = "[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...";
        final String bookSummary = bookService.getBookSummary("BOOK-WILL987");
        Assert.assertNotNull(bookSummary);
        Assert.assertEquals(expectedSummary, bookSummary);
    }
}
