N = int(input())
F = int(input())

begin_num = (N // 100) * 100
end_num = begin_num + 99

for num in range(begin_num, end_num+1):
  if num % F == 0:
    print("{:02d}".format(num % 100))
    break;