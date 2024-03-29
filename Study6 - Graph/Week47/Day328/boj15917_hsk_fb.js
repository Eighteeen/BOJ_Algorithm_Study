const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `10
1
2
7
4
14
32
33
34
35
36`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 자바스크립트 스타일 가이드에 따르면 함수 첫글자는 소문자 : 22
//// => 잉 대문자로 썻네여,, 왜그랬지
const canExpressedAsPower = (num) => {
  //// 조건문 그대로 return해도 돼용
  //// => 저는 이게 편해서 그대로 할게욧
  if ((num & -num) === num) return true;
  return false;
};

const Q = Number(input());
const result = [];
for (let i = 0; i < Q; i++) {
  const a = Number(input());
  result.push(canExpressedAsPower(a) ? 1 : 0);
}
console.log(result.join('\n'));
//// 깔끔
