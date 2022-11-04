class OrderedSet:
    def __init__(self):
        self.dic = {}

    def add(self, key, value):
        self.dic[key] = value

    def rem(self, key):
        del self.dic[key]

    def dec(self, key, value):
        self.dic[key] = value
        self.sort(key)

    def get(self):
        return self.dic

    def sort(self, key):
        key1 = list(self.dic.keys())
        key2 = list(self.dic.items())
        place = key1.index(key)
        place1 = place - 1
        while place1 >= 0 and key2[place1][1] > key2[place][1]:
            # self.dic[place], self.dic[place1] = self.dic[place1], self.dic[place]
            key2[place], key2[place1] = key2[place1], key2[place]
            place1 = place1 - 1
            place = place - 1
        self.dic = dict(key2)

