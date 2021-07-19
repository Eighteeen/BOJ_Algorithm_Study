const fs = require('fs');
let [n, height] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `7 3`)
  .split(' ')
  .map((value) => Number(value));

let resultNodeNum = 0;
let nodeNum = n - 1;

//// 문제를 깊이 이해하셔서 굉장히 효율적이고 깔끔한 코드가 나온 것 같습니다. 대단해요 : 22 진짜 간단하고 깔끔하게 잘 짜신 것 같습니다.
while (height > 0) {
  if (nodeNum % 2 === 1) {
    //// 오른쪽 자식으로 간다 = 현재 깊이의 노드 수의 절반만큼 nodeNum이 늘어난다 군요
    resultNodeNum += Math.pow(2, height - 1);
  }

  nodeNum = parseInt(nodeNum / 2);
  height -= 1;
}

console.log(resultNodeNum);
