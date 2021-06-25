const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
3 3
1 2
2 3
1 3
5 4
2 1
2 3
4 3
4 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 깔껌해요~ : 22
const T = parseInt(input());
for (let i = 0; i < T; i++) {
  const [N, M] = input()
    .split(' ')
    .map((item) => parseInt(item));
  for (let i = 0; i < M; i++) {
    input();
  }
  console.log(N - 1);
}
