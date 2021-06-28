const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
1 2 3
2 3 2
1 4 9
4 11 3
1 5 10
4 20 10
`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const tree = Array.from(Array(5001), () => Array());

const findLongLengthStreet = (nodeNum) => {
  if (!tree[nodeNum]) return 0;

  let connectedNodeList = tree[nodeNum];
  if (connectedNodeList.length < 1) return 0;

  let maxLength = 0;
  connectedNodeList.map((node) => {
    let length = node.length + findLongLengthStreet(node.connectedNode);
    maxLength = Math.max(maxLength, length);
  });

  return maxLength;
};

if (N === 1) {
  console.log(0);
  return;
}

for (let i = 1; i < N; i++) {
  const [A, B, C] = input()
    .split(' ')
    .map((value) => Number(value));
  tree[A].push({ connectedNode: B, length: C });
}

console.log(findLongLengthStreet(1));
