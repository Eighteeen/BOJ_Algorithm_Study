const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

//// 이런 방법으로도 부모를 구할 수 있군요 다른 방법도 알아갑니다!
const getParentNodeNum = (nodeNum, K) => {
  return Math.floor((nodeNum + K - 2) / K);
};

const getDistanceBetweenNodes = (nodeA, nodeB, K) => {
  let distance = 0;

  //// 요란하게 LCA 구할 필요 없이 고냥 이러면 되는군요.. 배워갑니다
  //// nodeA > nodeB 와 nodeB > nodeA 가 사실상 분기 처리인데 while문을 중복적으로 쓰는 게 약간은 어색해보입니다.
  //// => 한눈에 이해하기 편하게 한 것이었는데 오히려 독이 됐네요ㅠㅠ 흑,, 간단하게 변환했습니다.
  while (nodeA !== nodeB) {
    distance += 1;

    if (nodeA > nodeB) nodeA = getParentNodeNum(nodeA, K);
    else nodeB = getParentNodeNum(nodeB, K);
  }

  return distance;
};

//// 깔끔합니다 : 22
const input = [];
rl.on('line', (line) => {
  input.push(line);
}).on('close', () => {
  const [N, K, Q] = input[0].split(' ').map(Number);
  const result = [];
  for (let i = 1; i <= Q; i++) {
    let [nodeA, nodeB] = input[i].split(' ').map(Number);

    if (K === 1) {
      result.push(Math.abs(nodeA - nodeB));
      continue;
    }

    let distance = getDistanceBetweenNodes(nodeA, nodeB, K);
    result.push(distance);
  }
  console.log(result.join('\n'));

  process.exit();
});
