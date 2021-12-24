n, m = list(map(int, input().split(" ")))

if n*m % 2 == 0:
    print("%d" %(n*m//2))
else:
    print("%d" %((n*m-1)//2))