import json
from types import SimpleNamespace

import pygame
from pygame import display, RESIZABLE, gfxdraw
from pygame.color import Color

import Agent
import pokemon
import src_Ex3
from find_closestpoc import findClosest
from src_Ex3.DiGraph import DiGraph


# This func implicate the game
class Exx4:
    def __init__(self):
        self.PokemonList = {}
        self.AgentList = {}
        self.graph = src_Ex3.DiGraph.DiGraph()
        self.Algo = src_Ex3.GraphAlgo.GraphAlgo()
        self.pokemons = ""
        self.agents = ""
        self.i = 0
        self.Nodepos = []
        self.EdgesPos = []

    def putAgent(self, client):
        agents = json.loads(client.get_agents(),
                            object_hook=lambda d: SimpleNamespace(**d)).Agents
        self.agents = [agent.Agent for agent in agents]
        i = 0
        for a in self.agents:
            x, y, _ = a.pos.split(',')
            a.pos = SimpleNamespace(x=self.my_scale(
                float(x), x=True), y=self.my_scale(float(y), y=True))
            a1 = Agent.agent(a.id, a.src, a.dest, a.speed, (x, y, 0))

            self.AgentList[i] = a1
            i = i + 1

    def putPokemons(self, client):
        pokemons = json.loads(client.get_pokemons(),
                              object_hook=lambda d: SimpleNamespace(**d)).Pokemons

        self.pokemons = [p.Pokemon for p in pokemons]
        center = self.Algo.centerPoint()[0]
        list1 = [center, center, center, center]

        i = 0
        for p in self.pokemons:
            x, y, _ = p.pos.split(',')
            p.pos = SimpleNamespace(x=self.my_scale(
                float(x), x=True), y=self.my_scale(float(y), y=True))
            p1 = pokemon.pokemon(p.value, p.type, p.pos, (x, y, _), self.Algo.graph)

            self.PokemonList[i] = p1
            if i < 4:
                list1[i] = p1.src
            i = i + 1
        client.add_agent("{\"id\":" + str(list1[1]) + "}")
        client.add_agent("{\"id\":" + str(list1[0]) + "}")
        client.add_agent("{\"id\":" + str(list1[2]) + "}")
        client.add_agent("{\"id\":" + str(list1[3]) + "}")

    def getGraph(self, client):
        graph_json = client.get_graph()

        self.graph = json.loads(
            graph_json, object_hook=lambda json_dict: SimpleNamespace(**json_dict))
        obj = open("t.json", 'w')
        obj.write(graph_json)
        obj.close()
        self.Algo.load_from_json("t.json")
        self.graph = self.Algo.graph
        for e in self.graph.EdgeList:
            # find the edge nodes
            src = next(n for n in self.graph.get_all_v() if n[0] == e[0])
            dest = next(n for n in self.graph.get_all_v() if n[0] == e[1])
            self.EdgesPos.append((self.my_scale(src[1][0], x=True), self.my_scale(src[1][1], y=True),
                                  self.my_scale(dest[1][0], x=True), self.my_scale(dest[1][1], y=True)))
        for n in self.graph.get_all_v():
            self.Nodepos.append((n[0], self.my_scale(n[1][0], x=True), self.my_scale(n[1][1], y=True)))

    def scale(self, data, min_screen, max_screen, min_data, max_data):

        """
        get the scaled data with proportions min_data, max_data
        relative to min and max screen dimentions
        """
        return ((data - min_data) / (max_data - min_data)) * (max_screen - min_screen) + min_screen

    def my_scale(self, data, x=False, y=False):
        WIDTH, HEIGHT = 1080, 720
        # get data proportions
        min_x = min(list((dict(self.graph.get_all_v()).values())), key=lambda n: n[0])[0]
        min_y = min(list((dict(self.graph.get_all_v()).values())), key=lambda n: n[1])[1]
        max_x = max(list((dict(self.graph.get_all_v()).values())), key=lambda n: n[0])[0]
        max_y = max(list((dict(self.graph.get_all_v()).values())), key=lambda n: n[1])[1]
        screen = display.set_mode((WIDTH, HEIGHT), depth=32, flags=RESIZABLE)

        if x:
            return self.scale(data, 50, screen.get_width() - 50, min_x, max_x)
        if y:
            return self.scale(data, 50, screen.get_height() - 50, min_y, max_y)

    def Updatepoc(self, client):
        pokemons = json.loads(client.get_pokemons(),
                              object_hook=lambda d: SimpleNamespace(**d)).Pokemons

        pokemons = [p.Pokemon for p in pokemons]
        i = 0
        for p in pokemons:
            x, y, _ = p.pos.split(',')
            p.pos = SimpleNamespace(x=self.my_scale(
                float(x), x=True), y=self.my_scale(float(y), y=True))
            p1 = pokemon.pokemon(p.value, p.type, p.pos, (x, y, _), self.Algo.graph)
            if float(self.PokemonList[i].pos.x) != float(self.my_scale(float(x), x=True)) and float(
                    self.PokemonList[i].pos.y) != float(self.my_scale(float(y), x=True)):
                self.PokemonList[i] = p1
            i = i + 1
        # for i in range(len(self.PokemonList)):
        # print(self.PokemonList[i].pos)

    def UpdeateAgents(self, client):
        agents = json.loads(client.get_agents(),
                            object_hook=lambda d: SimpleNamespace(**d)).Agents
        agents = [agent.Agent for agent in agents]
        i = 0
        for a in agents:
            x, y, _ = a.pos.split(',')
            a.pos = (self.my_scale(float(x), x=True), self.my_scale(float(y), y=True), 0.0)
            self.AgentList[i].pos = a.pos
            self.AgentList[i].src = a.src
            self.AgentList[i].dest = a.dest
            self.AgentList[i].speed = a.speed
            self.AgentList[i].value = a.value
            i = i + 1

    def draw(self, screen):
        radius = 15

        # refresh surface
        # screen.fill(Color(0, 0, 0))
        # draw nodes
        self.i = 0

        for n in self.Nodepos:
            if self.i == 11:
                self.i = 0
            x = n[1]
            y = n[2]
            if self.i < 420:
                self.i = self.i + 1
            # its just to get a nice antialiased circle
            x1 = int(x)
            gfxdraw.filled_circle(screen, int(x1), int(y),
                                  radius, Color(64, 80, 174))
            gfxdraw.aacircle(screen, int(x), int(y),
                             radius, Color(255, 255, 255))

            # draw the node id
            FONT = pygame.font.SysFont('Arial', 20, bold=True)
            id_srf = FONT.render(str(n[0]), True, Color(255, 255, 255))
            rect = id_srf.get_rect(center=(x, y))
            screen.blit(id_srf, rect)
        # draw edges

        for e in self.EdgesPos:
            # scaled position
            src_x = e[0]  # self.my_scale(src[1][0], x=True)
            src_y = e[1]  # self.my_scale(src[1][1], y=True)
            dest_x = e[2]  # self.my_scale(dest[1][0], x=True)
            dest_y = e[3]  # self.my_scale(dest[1][1], y=True)

            # draw the line
            pygame.draw.line(screen, Color(61, 72, 126), (src_x, src_y), (dest_x, dest_y))
            self.i = self.i + 10

        # draw agents
        for i in range(len(self.AgentList)):
            agent = self.AgentList[i]
            pygame.draw.circle(screen, Color(122, 61, 23),
                               (int(agent.pos[0]), int(agent.pos[1])), 10)
        for i in range(len(self.PokemonList)):
            p = self.PokemonList[i]
            pygame.draw.circle(screen, pygame.Color(0, 255, 255), (p.pos.x, p.pos.y), 10)

        # update screen changes
        display.update()

    def choose_Agents(self, count, client):
        for i in range(len(self.AgentList)):
            t = self.AgentList[i]
            # print(t.id,t.poc)
            if t.stopList:
                # if abs(float(t.pos[0])-graph.Nodes[t.stopList[0]].pos.x)<0.008 and abs(float(t.pos[1])-graph.Nodes[t.stopList[0]].pos.y)<0.008 :
                next_node = t.stopList[0]
                # xn=self.graph.Nodelist[next_node][1][0]
                # yn=self.graph.Nodelist[next_node][1][1]
                # xs=self.graph.Nodelist[t.src][1][0]
                # ys=self.graph.Nodelist[t.src][1][1]
                # x1=self.my_scale(xn,x=True)
                # y1=self.my_scale(yn,y=True)
                # x2=self.my_scale(xs,x=True)
                # y2=self.my_scale(ys,y=True)
                # print(t.pos[0] - x1,t.pos[1]-y1,t.pos[0]-x2,t.pos[1]-y2)
                # if abs(t.pos[0]-x1)<10 and abs(t.pos[1]-y1)<10 or (t.pos[0]-x2<5 and t.pos[1]-y2<5):
                # client.move()
                pos1 = t.poc.pos

                x = float(pos1.x)
                y = float(pos1.y)
                if abs(t.pos[0] - x) < 5 and abs(t.pos[1] - y) <5 :
                    client.move()
                if t.src == t.stopList[0]:
                    t.stopList = t.stopList[1:]

                if t.dest == -1:
                    client.choose_next_edge(
                        '{"agent_id":' + str(t.id) + ', "next_node_id":' + str(next_node) + '}')
                    ttl = client.time_to_end()
                    print(ttl, client.get_info())

            # if the stoplist is empty find new Pokemon
            else:
                dist, t.stopList, t.poc = findClosest(self.PokemonList, self.Algo, t.src)
                t.poc.Agent = t
            # self.findClosest1()
        if count % (7 + len(self.PokemonList)/2-(len(self.AgentList)+1)/2) == 0:
            client.move()
