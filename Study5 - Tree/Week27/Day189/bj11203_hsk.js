const fs = require('fs');
let inputData = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3 LR`).split(' ');

const height = parseInt(inputData[0]);
const routeArr = inputData[1];
let nodeNum = 1;

const calcNodeNum = (routeArr) => {
  for (let route of routeArr) {
    if (route === 'L') nodeNum = nodeNum * 2;
    else nodeNum = nodeNum * 2 + 1;
  }

  return nodeNum;
};

const totalNodeNum = Math.pow(2, height + 1);

//// length가 음수, 0, 1일때를 체크하는게 아닌 오직 1일때만을 체크하는것이니 length == 1 조건이 더 직관적일 것 같습니다
if (inputData.length < 2) console.log(totalNodeNum - 1);
else console.log(totalNodeNum - calcNodeNum(routeArr));

//// 깔끔합니다