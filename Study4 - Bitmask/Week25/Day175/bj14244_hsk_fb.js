const fs = require('fs');
let [nodeCnt, leafCnt] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `4 `)
  .split(' ')
  .map((item) => parseInt(item));

//// 깔끔합니다~
//// saveNodeNum = breakNodePoint; 으로 설정하면 i + 1 연산을 할 필요가 없네요.
//// : 그렇네요
let breakNodePoint = nodeCnt - leafCnt;
let saveNodeNum = breakNodePoint;
for (let i = 0; i < breakNodePoint; i++) {
  console.log(`${i} ${i + 1}`);
}
for (let j = breakNodePoint; j < nodeCnt - 1; j++) {
  console.log(`${saveNodeNum} ${j + 1}`);
}
