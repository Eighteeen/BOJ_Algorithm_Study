const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
33 79
9 15
1022 1023`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const T = parseInt(input());

const findParentNode = (node) => {
  return parseInt(node / 2);
};

//// 깔끔하고 효율적입니다 : 22
for (let i = 0; i < T; i++) {
  let [A, B] = input()
    .split(' ')
    .map((value) => parseInt(value));

  while (A !== B) {
    if (A > B) A = findParentNode(A);
    else B = findParentNode(B);
  }
  console.log(10 * A);
}
