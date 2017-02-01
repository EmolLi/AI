import math
import random

x_MAX = 10
x_MIN = 0


def simulate_annealing_test():
    start_p = range(0, 11)
    steps = [0.4, 0.1, 0.08, 0.05]
    data = [['start point', 'step size', 'temperature', 'X', 'Y']]
    temps = [1000, 1000000, 10000000000]
    for start in start_p:
        for step in steps:
            for t in temps:
                i = 10000
                ymax = -1
                xmax = -1
                while i > 0:
                    y, x = simulated_annealing(start, step, t)
                    if y > ymax:
                        ymax = y
                        xmax = x
                        print y
                        print x
                    i -= 1
                data.append([start, step, t, xmax, ymax])
    return data


def target_function(x):
    return math.cos(math.pow(x, 2) / 2.0) / math.log(x + 2.0, 2)


def hill_climb_test():
    start_p = range(0, 11)
    steps = [x * 0.01 for x in range(1, 11)]
    results = [['start point', 'step size', 'X', 'Y', 'iteration']]
    for x in start_p:
        for step in steps:
            result = hill_climbing(x, step)
            iteration = len(result)
            X, Y = result[-1]
            results.append([x, step, X, Y, iteration])
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
        x1, candidate = go_down(x_now, step_size)

    return result


def get_p(new, old, t):
    exp = -(old - new)/t
    return math.exp(exp)


def simulated_annealing(start, step_size, temp):
    max = target_function(start)
    max_x = start
    t = temp
    cur = start
    nextv = -1
    Ei = -1
    #i = 1
    while t > 0.000000001:
        if random.randint(0, 1) == 0:
            nextv, Ei = go_up(cur, step_size)
        else:
            nextv, Ei = go_down(cur, step_size)
        if Ei == -1000:
            continue
        if Ei > max:
            cur = nextv
            max_x = nextv
            max = Ei
        else:
            ran = random.randint(0, 100)
            prob = 100*get_p(Ei, max, t)
            if ran < prob:
                cur = nextv

        t /= 2
        #i += 1
    #print i
    return max, max_x


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

