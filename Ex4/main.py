import pygame
from pygame import display, RESIZABLE

from Exx4 import Exx4
from client import Client


# The main of the game
class main:
    def __init__(self):
        self.poc = Exx4()
        self.screen = display.set_mode((1080, 720), depth=32, flags=RESIZABLE)
        self.client = Client()

    # run the game
    def run(self):
        PORT = 6666
        # server host (default localhost 127.0.0.1)
        HOST = '127.0.0.1'
        pygame.init()
        self.client.start_connection(HOST, PORT)
        self.client.start()
        # Get the graph,pokimons and Agents from the server
        self.poc.getGraph(self.client)  # Get the graph from the server
        self.poc.putPokemons(self.client)
        self.poc.putAgent(self.client)

        FONT = pygame.font.SysFont('Arial', 20, bold=True)
        pygame.font.init()
        list1 = []
        firsttime = 0
        clock = pygame.time.Clock()
        self.client.start()

        while self.client.is_running() == 'true':
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    exit(0)
            # Update the pokemons loc loc and agents loc
            self.poc.Updatepoc(self.client)
            self.poc.UpdeateAgents(self.client)
            self.poc.draw(self.screen)
            self.poc.choose_Agents(firsttime, self.client)

            clock.tick(60)
            firsttime = firsttime + 1


if __name__ == '__main__':
    x = main()
    x.run()
