password, prime_min_limit = map(int, input().split(' '))

min_prime = 0

for i in range(2, prime_min_limit):
  if password % i == 0:
    min_prime = i
    break

if min_prime == 0:
  print("GOOD")
else:
  print("BAD", min_prime)

## GOOD