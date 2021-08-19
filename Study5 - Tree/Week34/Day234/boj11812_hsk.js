const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const getParentNodeNum = (nodeNum, K) => {
  return Math.floor((nodeNum + K - 2) / K);
};

const getDistanceBetweenNodes = (nodeA, nodeB, K) => {
  let distance = 0;

  //// 요란하게 LCA 구할 필요 없이 고냥 이러면 되는군요.. 배워갑니다
  while (nodeA !== nodeB) {
    while (nodeA > nodeB) {
      distance += 1;
      nodeA = getParentNodeNum(nodeA, K);
    }
    while (nodeB > nodeA) {
      distance += 1;
      nodeB = getParentNodeNum(nodeB, K);
    }
  }

  return distance;
};

//// 깔끔합니다
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
