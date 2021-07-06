const fs = require('fs');
let inputData = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3 LR`).split(' ');

const height = parseInt(inputData[0]);
//// index out이 안 나다니... 어메이징 js
const routeArr = inputData[1];

//// nodeNum이 아래 calcNodeNum 함수 내에서만 쓰이니 nodeNum을 아래 함수 내에서 선언하는 게 깔끔할 것 같습니다.
//// => 넹 감사합니다
const calcNodeNum = (routeArr) => {
  let nodeNum = 1;

  for (let route of routeArr) {
    if (route === 'L') nodeNum = nodeNum * 2;
    else nodeNum = nodeNum * 2 + 1;
  }

  return nodeNum;
};

const totalNodeNum = Math.pow(2, height + 1);

//// length가 음수, 0, 1일때를 체크하는게 아닌 오직 1일때만을 체크하는것이니 length == 1 조건이 더 직관적일 것 같습니다 : 22
//// => 넹 감사합니다
if (inputData.length === 1) console.log(totalNodeNum - 1);
else console.log(totalNodeNum - calcNodeNum(routeArr));

//// 깔끔합니다
