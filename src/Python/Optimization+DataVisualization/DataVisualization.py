from openpyxl import Workbook


def newWorkbook():
    wb = Workbook()
    ws = wb.active
    return ws, wb


def gen_graph(data, ws):
    for row in data:
        ws.append(row)


def save(wb):
    wb.save("dataAnalysze.xlsx")