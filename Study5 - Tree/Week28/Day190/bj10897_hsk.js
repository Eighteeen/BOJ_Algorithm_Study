const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
1 2 1 2`
).split('\n');

//// 전반적으로 변수명에 index 용어가 들어갔는데,
  //// index라고 하기엔 1부터 시작하기 때문에 nth, sequence 정도의 변수명이 적합해보입니다.

//// D의 경우 100 이하인데 BigInt를 사용하는 점이 아쉽습니다.
  //// (아래에서 D를 사용하는 i도 BigInt로 변경해주기 때문에 필요성이 없어보입니다.)
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
  //// generation = i 인데 필요없는 연산과 변수같습니다.
  let generation = generationAllNodeCnt / prevGenerationNodeCnt;

  let childIdx = childIdxArray[i - 1];
  //// prev1stChildNodeIndex가 prevChildNodeIdx에 저장되지만
    //// 연산되는 시점은 현재 세대에 대한 정보를 연산하므로 prev를 current로 변경하거나 prev가 빠지는 게 가독에 오해가 없을 것 같습니다.
  let prev1stChildNodeIndex = generation * (prevChildNodeIdx - BigInt(1)) + childIdx;

  remainderAccumulateNode = (prevNodeNum + prev1stChildNodeIndex) % mod;
  findNodeNumResult.push(remainderAccumulateNode);

  prevNodeNum = prevNodeNum + generationAllNodeCnt;
  prevGenerationNodeCnt = generationAllNodeCnt;
  prevChildNodeIdx = prev1stChildNodeIndex;
}

console.log(findNodeNumResult.join('\n'));
