burger = 2000
for i in range(3):
  burger = min(burger, int(input()))

beverage = 2000
for i in range(2):
  beverage = min(beverage, int(input()))

print(burger+beverage-50)

## 깔끔