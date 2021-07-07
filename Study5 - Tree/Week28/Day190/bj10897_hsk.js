const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
1 1 1 2`
).split('\n');

const D = BigInt(stdin[0]);
const childIdxArray = stdin[1].split(' ').map((value) => BigInt(value));
const mod = BigInt(1000000007);

let prevGenerationNodeCnt = BigInt(1);
let prevNodeNum = BigInt(0);
let prevChildNodeIdx = BigInt(1);

let remainderAccumulateNode = 0;
let findNodeNumResult = [];

//// 짧은 영어단어의 나열로도 충분히 의미를 전달할 수 있는 것 같네요. 영문법을 꽤 많이 신경써왔었던 저로서 느끼는 점이 있었습니다
for (let i = 1; i <= D; i++) {
  let generationAllNodeCnt = prevGenerationNodeCnt * BigInt(i);
  let generation = generationAllNodeCnt / prevGenerationNodeCnt;

  let childIdx = childIdxArray[i - 1];
  let prev1stChildNodeIndex = generation * (prevChildNodeIdx - BigInt(1)) + childIdx;

  remainderAccumulateNode = (prevNodeNum + prev1stChildNodeIndex) % mod;
  findNodeNumResult.push(remainderAccumulateNode);

  prevNodeNum = prevNodeNum + generationAllNodeCnt;
  prevGenerationNodeCnt = generationAllNodeCnt;
  prevChildNodeIdx = prev1stChildNodeIndex;
}

console.log(findNodeNumResult.join('\n'));
