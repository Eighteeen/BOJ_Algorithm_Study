const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 4
1 2
2 3
3 4
4 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [N, M] = input().split(' ').map(Number);
const friendsGraph = Array.from(new Array(N + 1), () => new Array(N + 1).fill(0));
const maxNumOfFriends = (N * (N - 1)) / 2;
let cntNumOfFriends = M;

for (let i = 0; i < M; i++) {
  const [personA, personB] = input().split(' ').map(Number);

  friendsGraph[personA][personB] = 1;
  friendsGraph[personB][personA] = 1;
}

while (cntNumOfFriends < maxNumOfFriends) {
  // for (){
  //   for (){
  //     for (){
  //     }
  //   }
  // }
}
console.log(friendsGraph);
