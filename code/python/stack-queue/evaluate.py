from stack import Stack


def compute(expr: str) -> float:
    """
    Dijkstra's two-stack algorithm for expression evaluation.

    :param expr: an arithmetic expression
    :return: computed value
    """
    ops = Stack()
    vals = Stack()
    expr = expr.split(' ')
    for c in expr:
        if c == '(':
            continue
        elif c in '+-*/':
            ops.push(c)
        elif c == ')':
            v = vals.pop()
            op = ops.pop()
            if op == '+':
                v = vals.pop() + v
            elif op == '-':
                v = vals.pop() - v
            elif op == '*':
                v = vals.pop() * v
            elif op == '/':
                v = vals.pop() / v
            vals.push(v)
        else:
            vals.push(float(c))
    return vals.pop()
