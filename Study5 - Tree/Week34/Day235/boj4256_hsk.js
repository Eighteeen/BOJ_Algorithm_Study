const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
4
3 2 1 4
2 3 4 1
8
3 6 5 4 8 7 1 2
5 6 8 4 3 1 2 7`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const traversalPostOrder = (start, end, cur) => {
  for (let i = start; i < end; i++) {
    if (preOrderList[cur] !== inOrderList[i]) continue;

    traversalPostOrder(start, i, cur + 1);
    traversalPostOrder(i + 1, end, cur + 1 + i - start);
    postOrderList.push(inOrderList[i]);
  }
};

//// 깔끔
const T = parseInt(input());
let preOrderList = [];
let inOrderList = [];
let postOrderList = [];
for (let i = 0; i < T; i++) {
  const N = parseInt(input());
  preOrderList = input().split(' ').map(Number);
  inOrderList = input().split(' ').map(Number);
  postOrderList = [];

  traversalPostOrder(0, N, 0);

  console.log(postOrderList.join(' '));
}
