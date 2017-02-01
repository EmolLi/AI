from Optimization import *
from DataVisualization import *


def main():

    data = simulate_annealing_test()
    ws, wb = newWorkbook()
    gen_graph(data, ws)
    save(wb)

if __name__ == "__main__":
    main()

