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

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

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
