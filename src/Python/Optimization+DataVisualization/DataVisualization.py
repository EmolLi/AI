import plotly.plotly as py
import plotly.graph_objs as go


def gen_graph(x_list, y_list, f_name):
    trace = go.Scatter(
        x = x_list,
        y = y_list,
        mode = 'markers'
    )
    data = [trace]


    # Plot and embed in ipython notebook!
    py.plot(data, filename= f_name)

# or plot with: plot_url = py.plot(data, filename='basic-line')