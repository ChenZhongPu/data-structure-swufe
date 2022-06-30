class ShopCart:
    def __init__(self):
        self.carts = {}

    def size(self):
        return len(self.carts)

    def add_book(self, name, v=1):
        amount = self.carts.get(name, 0) + v
        self.carts[name] = amount

    def get_amount(self, name):
        return self.carts.get(name, 0)


if __name__ == '__main__':
    shop_cart = ShopCart()
    shop_cart.add_book("8899")

    assert shop_cart.size() == 1
    assert shop_cart.get_amount('8899') == 1
    assert shop_cart.get_amount('7788') == 0

    shop_cart.add_book('7788', 2)
    assert shop_cart.get_amount('7788') == 2

    shop_cart.add_book('7788')
    assert shop_cart.get_amount('7788') == 3

    assert shop_cart.size() == 2
