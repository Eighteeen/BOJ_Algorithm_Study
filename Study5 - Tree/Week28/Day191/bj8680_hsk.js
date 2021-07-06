const fs = require('fs');
let [n, height] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `7 3`)
  .split(' ')
  .map((value) => Number(value));

let resultNodeNum = 0;
let nodeNum = n - 1;

while (height > 0) {
  if (nodeNum % 2 === 1) {
    resultNodeNum += Math.pow(2, height - 1);
  }

  nodeNum = parseInt(nodeNum / 2);
  height -= 1;
}

console.log(resultNodeNum);
