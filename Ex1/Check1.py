import pandas as pd
import json
import csv


def allocate(Building='B1.json', calls='Calls_a.csv', output='a.log'):
    json_file = open(Building, encoding="utf-8")
    thisBuild = json.load(json_file)
    json_file.close()
    elev = len(thisBuild['_elevators'])
    Headers = ['Call', 'time', 'src', 'dest', 'flag', 'elev']
    with open(calls) as file:
        reader = csv.DictReader(file, fieldnames=Headers)
        with open('B3_Cb1.csv', 'w', newline='') as csvF:
            writer = csv.DictWriter(csvF, fieldnames=reader.fieldnames)
            writer.writeheader()
            next(reader)
            writer.writerows(reader)
    df = pd.read_csv('B3_Cb1.csv')
    NOC = (int(df.size / len(df.columns)))
    for i in range(NOC):
        t = i % elev
        df.iloc[i:i + 1, 5:6] = t
    df.to_csv('B3_Cb1.csv', index=False)
    with open('B3_Cb1.csv') as f1:
        with open(output, 'w') as f2:
            next(f1)
            for line in f1:
                f2.write(line)


if __name__ == '__main__':
    allocate('B2.json', 'calls_a.csv', 'B2_CA.csv')
