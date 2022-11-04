import pokemon


class agent:
    def __init__(self, ID, src, dest, speed, pos: (0, 0, 0)):
        self.id = ID
        self.src = src
        self.dest = dest
        self.speed = speed
        self.pos = pos
        self.stopList = []
        self.Target = False
        self.value = 0
        self.prevpos = pos
        self.poc = pokemon
