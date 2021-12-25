n, m = list(map(int, input().split(" ")))

if n*m % 2 == 0:
    print("%d" %(n*m//2))
else:
    print("%d" %((n*m-1)//2))
## 왜 따로따로..? 그냥 n*m//2 로 다 가능합니다 : 22