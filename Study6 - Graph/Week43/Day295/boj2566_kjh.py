max_value = 0
max_row = 0
max_col = 0

for i in range(9):
  nums = list(map(int, input().split(' ')))
  for j in range(9):
    if nums[j] > max_value:
      max_value = nums[j]
      max_row = i + 1
      max_col = j + 1

print(max_value)
print(max_row, max_col)
## 깔끔 : 22