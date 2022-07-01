class Coffee:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def __str__(self):
        return f'Coffee(name: {self.name}, price: {self.price})'


class CoffeeDB:
    def __init__(self):
        self.coffees = [Coffee('Mocha', 10),
                        Coffee('Latte', 12),
                        Coffee('Mocha with Milk', 11),
                        Coffee('Espresso', 15),
                        Coffee('Espresso with Ice', 15),
                        Coffee('Americano', 8),
                        Coffee('Americano with Ice', 8)]

    def add_coffee(self, name, price):
        self.coffees.append(Coffee(name, price))

    def find_by_name(self, name):
        # Please complete this method
        return []


if __name__ == '__main__':
    db = CoffeeDB()
    r = db.find_by_name('Ice')
    for c in r:
        print(c)
