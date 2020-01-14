package com.sky.library;

public class BookServiceImpl implements BookService {

    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final int REVIEW_WORD_LENGTH = 9;

    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book retrieveBook(final String bookReference) throws BookNotFoundException {
        validate(bookReference);
        final Book book = bookRepository.retrieveBook(bookReference);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    public String getBookSummary(final String bookReference) throws BookNotFoundException {
        final Book book = retrieveBook(bookReference);
        return "[" + book.getReference() + "] " + book.getTitle() + " - " + StringUtil.truncate(book.getReview(), REVIEW_WORD_LENGTH);
    }

    private void validate(final String bookReference) {
        if (!bookReference.startsWith(BOOK_REFERENCE_PREFIX)) {
            throw new ValidationException("Book reference must begin with BOOK-");
        }
    }
}
