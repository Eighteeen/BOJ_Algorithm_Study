const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `0/0/1`).split('/');

//// 무난 깔끔
//// map과 비구조화 할당 이용해서 한번에 집어넣는건 어떤가요
//// 이 문제 풀면서 '아.. 이거 내가 사랑하는 js 문법이였으면 비구조화 할당으로 깔꼼하게 집어넣는건데' 이런 생각했어서 괜히 아쉬워요 ㅠㅠㅋㅋ
//// -> 반영했습니다:)
const [K, D, A] = input.map((value) => parseInt(value));

const isReal = (K, D, A) => {
  if (K + A < D || D === 0) return false;
  return true;
};

console.log(isReal(K, D, A) ? 'gosu' : 'hasu');
