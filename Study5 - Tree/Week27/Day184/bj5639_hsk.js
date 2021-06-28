const fs = require('fs');
const preOrderList = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `50
30
24
5
28
45
98
52
60`
)
  .split('\n')
  .map((value) => Number(value));

const getPostOrderList = (left, right) => {
  if (left > right) return;

  let root = preOrderList[left];
  let dividedPoint = right + 1;

  for (let i = left + 1; i <= right; i++) {
    if (preOrderList[i] < root) continue;
    dividedPoint = i;
    break;
  }

  getPostOrderList(left + 1, dividedPoint - 1);
  getPostOrderList(dividedPoint, right);

  postPorderList.push(root);
};

const postPorderList = [];
getPostOrderList(0, preOrderList.length - 1);

console.log(postPorderList.join('\n'));
