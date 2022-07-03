from stack import Stack


def is_matched(expr):
    """
    Check whether an expression is matched in terms of parentheses

    :param expr: an expression with parentheses
    :return: True if matched; False otherwise
    """
    openings = '([{'
    closings = ')]}'
    s = Stack()
    for c in expr:
        if c in openings:
            s.push(c)
        elif c in closings:
            if s.is_empty():
                return False
            if closings.index(c) != openings.index(s.pop()):
                return False
    return s.is_empty()


