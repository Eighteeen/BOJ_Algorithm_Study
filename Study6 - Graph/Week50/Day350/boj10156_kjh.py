PRICE, QUANTITY, MONEY_HAVE = map(int, input().split())

money_to_buy = PRICE * QUANTITY
money_need_more = max(0, money_to_buy - MONEY_HAVE)

print(money_need_more)

## ㄲㄲ : 22