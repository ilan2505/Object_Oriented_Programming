

import math

from datetime import time


class PrimeRange:

    def __init__(self, start=1, end=10):
        if start < 0 or end < 1:
            raise Exception("range is undefined")
        self.start = start
        self.end = end
        self.curr = start
        self.__author__ = "Simon Pikalov"


    def __iter__(self):
        return self

    def __next__(self):
        for num in range(self.curr, self.end + 1):
            if num > 1:
                for i in range(2, int(math.sqrt(num) + 1)):
                    if (num % i) == 0:
                        break
                else:
                    self.curr = num + 1
                    return num
            if num == self.end:
                raise StopIteration


if __name__ == '__main__':
    for i in PrimeRange(1, 100):
        print(i)
    lp=[p for p in PrimeRange(0,1000)]
    print(lp)
    l=[0]*100000
    print(l)
    l=[0 for i in range(100000)]
    # for i in range(100000):
    #     l.append(0)
    print(l)





