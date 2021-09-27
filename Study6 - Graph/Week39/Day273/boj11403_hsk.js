const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
0 0 0 1 0 0 0
0 0 0 0 0 0 1
0 0 0 0 0 0 0
0 0 0 0 1 1 0
1 0 0 0 0 0 0
0 0 0 0 0 0 1
0 0 1 0 0 0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const graph = Array.from(new Array(N), () => input().split(' ').map(Number));

for (let k = 0; k < N; k++) {
  for (let i = 0; i < N; i++) {
    //// 올.. 이러면 훨 간단하네요
    for (let j = 0; j < N; j++) {
      if (graph[i][j]) continue;
      if (graph[i][k] && graph[k][j]) graph[i][j] = 1;
    }
  }
}

console.log(graph.map((vertices) => vertices.join(' ')).join('\n'));
