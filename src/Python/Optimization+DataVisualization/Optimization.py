import math

x_MAX = 10
x_MIN = 0


def target_function(x):
    return math.cos(x*x/2) / math.log(x+2, 2)


def hill_climb_test():
    start_p = range(0, 11)
    steps = [x * 0.01 for x in range(1, 11)]
    results = []
    for x in start_p:
        for step in steps:
            results.append((x, step, hill_climbing(x, step)))
    return results


def hill_climbing(x, step_size):
    if x == 1:
        x = 1
    result = []
    x_now = x
    cur = target_function(x)
    result.append((x_now, cur))

    x1, candidate = go_up(x_now, step_size)
    # go up
    while (cur < candidate) and (x_now < x_MAX):
        x_now, cur = update(x1, candidate, result)
        x1, candidate = go_up(x_now, step_size)
    if cur != target_function(x):
        return result

    # go down
    x1, candidate = go_down(x_now, step_size)
    while (cur < candidate) and (x_now > x_MIN):
        x_now, cur = update(x1, candidate, result)
        x1, candidate = go_down(x_now, candidate)
    return result


def update(x, cur, result):
    result.append((x, cur))
    return x, cur


def go_up(x, step):
    x1 = x + step
    if x1 > x_MAX:
        return x1, -1000
    candidate = target_function(x1)
    return x1, candidate


def go_down(x, step):
    x1 = x - step
    if x1 < x_MIN:
        return x1, -1000
    candidate = target_function(x1)
    return x1, candidate

