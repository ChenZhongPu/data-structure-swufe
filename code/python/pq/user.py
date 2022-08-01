class User:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return f"Name: {self.name}, age: {self.age}"


def user_cmp(self, other):
    return self.age < other.age


if __name__ == '__main__':
    User.__lt__ = user_cmp
    users = [User('Mary', 18), User('Ada', 30), User('Bob', 4)]
    users.sort()
    for u in users:
        print(u)
