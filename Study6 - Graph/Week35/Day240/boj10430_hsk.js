const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5 8 4`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [A, B, C] = input().split(' ').map(Number);
const result = [];

result.push((A + B) % C);
result.push(((A % C) + (B % C)) % C);
result.push((A * B) % C);
result.push(((A % C) * (B % C)) % C);

console.log(result.join('\n'));
//// 까루끄무 : 22 지금도 충분히 깔끔하긴 하지만 문제에서 원하는 것은 중복 연산을 줄여보라는 것 같아서 한번 생각해보는 것도 좋을 것 같습니다!