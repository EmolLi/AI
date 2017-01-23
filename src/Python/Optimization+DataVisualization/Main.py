from Optimization import *
from DataVisualization import *


def main():
    results = hill_climb_test()
    start, step, result = results[31]
    f_name = str(start) + "_" + str(step)
    xlist, ylist = zip(*result)
    gen_graph(xlist, ylist, f_name)


if __name__ == "__main__":
    main()

