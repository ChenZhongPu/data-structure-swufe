class Book:
    def __init__(self, name, price, author):
        self.name = name
        self.price = price
        self.author = author

    def __str__(self):
        return f"Name: {self.name}, price: {self.price}, author: {self.author}"

    def __lt__(self, other):
        return self.price < other.price
