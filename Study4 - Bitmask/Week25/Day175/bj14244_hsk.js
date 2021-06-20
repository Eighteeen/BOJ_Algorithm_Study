const fs = require('fs');
let [nodeCnt, leafCnt] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5 4`)
  .split(' ')
  .map((item) => parseInt(item));

let nodeNum = 0;
for (let i = 0; i < nodeCnt - leafCnt; i++) {
  console.log(`${i} ${i + 1}`);
  nodeNum = i + 1;
}
for (let j = nodeCnt - leafCnt; j < nodeCnt - 1; j++) {
  console.log(`${nodeNum} ${j + 1}`);
}
