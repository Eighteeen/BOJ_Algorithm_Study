const fs = require('fs');
let [A, B] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3 4`)
  .split(' ')
  .map((item) => parseInt(item));


//// 깔꼬미요 저랑 똑같네요
//// 깔끔
let leftCnt = 0;
let rightCnt = 0;
while (1) {
  if (A === 1) {
    rightCnt += B - 1;
    break;
  } else if (B === 1) {
    leftCnt += A - 1;
    break;
  }
  if (A > B) {
    leftCnt += parseInt(A / B);
    A %= B;
    continue;
  }
  rightCnt += parseInt(B / A);
  B %= A;
}
console.log(`${leftCnt} ${rightCnt}`);
