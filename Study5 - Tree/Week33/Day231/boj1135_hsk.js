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

const getTimeToDeleverNews = (node) => {
  if (companyTree[node].length === 0) return 0;

  let timeList = [];
  for (let nextNode of companyTree[node]) {
    timeList.push(getTimeToDeleverNews(nextNode));
  }

  timeList.sort((a, b) => b - a);
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
// 전달하는 시간이 오래걸리는 사원부터 먼저 전달
// 각 직속 부하들로부터 완료되는 전달 시간들을 오름차순으로 정렬
// 정렬한 순서에서 직속 부하에게 직접 전달하는 시간들을 더해 최댓값을 구함
// ex) 첫번째로 전달하는 직속부하는 + 1 , 두번쨰는 +2 ---으로 구함
