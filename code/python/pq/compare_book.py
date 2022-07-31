from book import Book
from functools import cmp_to_key

if __name__ == '__main__':
    books = [Book("Gone with the wind", 89, "Margaret Mitchell"),
             Book("Data structures", 120, "Unknown"),
             Book("The old man and the sea", 36, "Ernest Hemingway")]

    books.sort()

    for a in books:
        print(a)
    print('---')

    books.sort(key=lambda book: book.price)

    for a in books:
        print(a)
    print('---')

    def book_title(b):
        return b.name

    books.sort(key=book_title)

    for a in books:
        print(a)
    print('---')

    def book_title_len(b1: Book, b2: Book):
        """the longer first"""
        if len(b1.name) < len(b2.name):
            return 1
        elif len(b1.name) > len(b2.name):
            return -1
        else:
            return 0

    books.sort(key=cmp_to_key(book_title_len))

    for a in books:
        print(a)

