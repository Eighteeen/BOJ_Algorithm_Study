quantity=input()
for i in range(1, int(quantity)+1):
    plus=input()
    x=int(plus.split(' ')[0])
    y=int(plus.split(' ')[1])
    print('Case #%d: %d + %d = %d' % (i, x, y, x + y))
#### 깔끔해요 : 22