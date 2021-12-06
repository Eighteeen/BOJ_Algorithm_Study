CARDS, CARDS_FRONT_O, CARDS_BACK_O = map(int, input().split())

cards_o = min(CARDS_FRONT_O, CARDS_BACK_O)
cards_x = min(CARDS - CARDS_FRONT_O, CARDS - CARDS_BACK_O)

print(cards_o + cards_x)