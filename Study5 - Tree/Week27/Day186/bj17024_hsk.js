const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
1 2
4 3
2 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const edgeCntInNode = new Array(N + 1).fill(0);

for (let i = 1; i < N; i++) {
  let [nodeNumA, nodeNumB] = input()
    .split(' ')
    .map((node) => parseInt(node));
  edgeCntInNode[nodeNumA] += 1;
  edgeCntInNode[nodeNumB] += 1;
}

console.log(Math.max.apply(null, edgeCntInNode) + 1);
