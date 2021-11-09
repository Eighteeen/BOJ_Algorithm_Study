const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6
10 20
2 7
15 62
10 37
11 50
34 59`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 아하 BFS가 문제 의도였구나~
const getCntOfKick = (S, T) => {
  const queue = [];
  queue.push([S, T, 0]);

  while (queue.length) {
    const [myPoint, opponentPoint, cntOfKick] = queue.shift();
    if (myPoint <= opponentPoint) {
      queue.push([myPoint * 2, opponentPoint + 3, cntOfKick + 1]);
      queue.push([myPoint + 1, opponentPoint, cntOfKick + 1]);

      if (myPoint === opponentPoint) return cntOfKick;
    }
  }
};

const TEST_CASE = parseInt(input());
for (let i = 0; i < TEST_CASE; i++) {
  const [S, T] = input().split(' ').map(Number);

  const cntOfKick = getCntOfKick(S, T);
  console.log(cntOfKick);
}
//// 깔끔!
