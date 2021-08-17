const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6
-1 0 0 1 1 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 깔끔
const getTimeToDeleverNews = (node) => {
  if (companyTree[node].length === 0) return 0;

  let timeList = [];
  for (let nextNode of companyTree[node]) {
    timeList.push(getTimeToDeleverNews(nextNode));
  }

  //// 내림차순을 이런식으로 나타낼 수 있군요! js 또 알아갑니다
  timeList.sort((a, b) => b - a);
  //// 취향의 차이일 수도 있지만 for 조건문에서 i++ 를 제외하고,
  //// 실행문에서 timeList[i] += ++i; 로 처리하면 연산을 한번에 할 수 있습니다!
  //// => 아! 네네 알고있습니다. 그렇지만 저는 전위, 후위 연산자보다는 += 1 형식을 선호하기 떄문에
  //// => 앞으로도 필요한 경우 아니면 사용하지 않을 것 같습니다.
  //// => 알려주셔서 감사합니당^0^
  for (let i = 0; i < timeList.length; i++) {
    timeList[i] += i + 1;
  }

  return Math.max.apply(null, timeList);
};

const N = parseInt(input());
const companyTree = Array.from(new Array(N + 1), () => new Array());
const bossNum = input().split(' ').map(Number);
for (let i = 1; i < N; i++) {
  companyTree[bossNum[i]].push(i);
}

let minTimeToDeleverNews = getTimeToDeleverNews(0);
console.log(minTimeToDeleverNews);

// 로직 생각하기가 매우 힘들었음
// 전달하는 시간이 오래걸리는 사원부터 먼저 전달하는 방식
// 각 직속 부하들로부터 완료되는 전달 시간들을 오름차순으로 정렬
// 정렬한 순서에서 직속 부하에게 직접 전달하는 시간들을 더해 최댓값을 구함
// ex) 첫번째로 전달하는 직속부하는 + 1 , 두번쨰는 +2 ---으로 구함
