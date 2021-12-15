dices = list(map(int, input().split()))

prize_level = 1
prize_dice = max(dices)

for i in range(3):
  for j in range(i + 1, 3):
    if dices[i] == dices[j]:
      prize_dice = dices[i]
      prize_level += 1

if prize_level == 1:
  print(prize_dice * 100)
elif prize_level == 2:
  print(1000 + prize_dice * 100)
else:
  print(10000 + prize_dice * 1000)

# for문 활용 매우 깔끄음 : 22