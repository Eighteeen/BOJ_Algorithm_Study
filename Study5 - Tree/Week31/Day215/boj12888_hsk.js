const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `60`).split('\n');
const H = BigInt(input[0]);
const numOfCarNeeded = [BigInt(1), BigInt(1)];

for (let i = 2; i <= H; i++) {
  if (i % 2 === 0) {
    numOfCarNeeded[i] = BigInt(2) * numOfCarNeeded[i - 1] + BigInt(1);
  } else {
    numOfCarNeeded[i] = BigInt(2) * numOfCarNeeded[i - 1] - BigInt(1);
  }
}

console.log(numOfCarNeeded[H].toString());
// MAX_SAFE_INTEGER보다 큰 값들은 BigInt로 계산을 하고 숫자를 문자열로 바꿔서 제출하여 성공하였습니다.
