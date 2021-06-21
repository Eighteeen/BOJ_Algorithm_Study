const fs = require('fs');
let [nodeCnt, leafCnt] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5 4`)
  .split(' ')
  .map((item) => parseInt(item));

//// 깔끔합니다~
let saveNodeNum = 0;
let breakNodePoint = nodeCnt - leafCnt;
for (let i = 0; i < breakNodePoint; i++) {
  console.log(`${i} ${i + 1}`);
  saveNodeNum = i + 1;
}
for (let j = breakNodePoint; j < nodeCnt - 1; j++) {
  console.log(`${saveNodeNum} ${j + 1}`);
}
