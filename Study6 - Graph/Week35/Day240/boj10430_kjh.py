a, b, c = map(int, input().split())
print((a+b)%c)
print((a%c+b%c)%c)
print((a*b)%c)
print((a % c * b % c) % c)

## 깔끔 : 22 지금도 충분히 깔끔하긴 하지만 문제에서 원하는 것은 중복 연산을 줄여보라는 것 같아서 한번 생각해보는 것도 좋을 것 같습니다!