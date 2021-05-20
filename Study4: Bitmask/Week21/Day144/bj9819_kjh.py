import sys

def bit_count(num):
    count = 0
    while num:
        num &= num - 1
        count += 1
    return count

def solve(n, m, k):
  pieces = 0;
  for i in range(1, m + 1):
    pieces |= 1 << (n - i)
  
  for i in range(k):
    bottomBit = pieces & 1
    xor_with = (pieces >> 1) | (bottomBit << (n - 1))

    pieces = pieces ^ xor_with
  
  return bit_count(pieces)

# 128비트 비트마스크 자바 구현이 너무 번거로워서 파이썬으로 풀었음
for line in sys.stdin:
  line = line.replace('\n', '')
  if (line == '-1'):
    break

  n, m, k = map(int, line.split(','))
  answer = solve(n, m, k)
  print('%s: %d' % (line, answer))
