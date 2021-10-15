import decimal

N = int(input())
result = format(2 ** -N, '.300f').rstrip('0')

print(result)