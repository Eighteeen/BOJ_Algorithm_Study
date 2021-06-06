const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1
1 0
0 0
0 0
0 0
0 0
0 0
0 0
0 0
0 0`
).split('\n');

//// 깔끔
const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 함수로 뺐다면 team1, team2 등 추상화하면 더 좋을 것 같아요!
//// => 다음부터는 고려해서 문제를 풀어보겠습니다~
const getGameResult = (yonsei, korea) => {
  if (yonsei > korea) return 'Yonsei';
  else if (korea > yonsei) return 'Korea';
  else return 'Draw';
};

const T = parseInt(input());
for (let i = 0; i < T; i++) {
  let yonseiPoint = 0;
  let koreaPoint = 0;
  for (let i = 0; i < 9; i++) {
    const [yonsei, korea] = input()
      .split(' ')
      .map((item) => parseInt(item));

    yonseiPoint += yonsei;
    koreaPoint += korea;
  }
  console.log(getGameResult(yonseiPoint, koreaPoint));
}
