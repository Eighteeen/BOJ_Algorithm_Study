const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 4
1 2
1 3
1 4
1 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

// 처음으로 인접행렬로 풀어보았는데 많이 어렵네요
// 오늘 풀이도 뭔가 만족스럽지 못해요ㅠ 매우 속상
const [N, M] = input().split(' ').map(Number);
const friendsGraph = Array.from(new Array(N + 1), () => new Array(N + 1).fill(0));
const friendsGraphOfFriends = Array.from(new Array(N + 1), () => new Array(N + 1).fill(0));
let cntNumOfFriends = 0;
let newFriendsNumOnDays = [];

for (let i = 0; i < M; i++) {
  const [personA, personB] = input().split(' ').map(Number);

  [friendsGraph[personA][personB], friendsGraph[personB][personA]] = [1, 1];
  [friendsGraphOfFriends[personA][personB], friendsGraphOfFriends[personB][personA]] = [1, 1];

  cntNumOfFriends += 2;
}

while (cntNumOfFriends < N * (N - 1)) {
  let pairOfFriends = 0;

  for (let me = 1; me <= N; me++) {
    for (let myFriends = 1; myFriends <= N; myFriends++) {
      for (let friendsOfMyFriend = 1; friendsOfMyFriend <= N; friendsOfMyFriend++) {
        if (me !== friendsOfMyFriend && friendsGraph[me][myFriends] && friendsGraph[myFriends][friendsOfMyFriend]) {
          friendsGraphOfFriends[me][friendsOfMyFriend] = 1;
          friendsGraphOfFriends[friendsOfMyFriend][me] = 1;
        }
      }
    }
  }

  for (let me = 1; me <= N; me++) {
    for (let myFriends = 1; myFriends <= N; myFriends++) {
      if (!friendsGraph[me][myFriends] && friendsGraphOfFriends[me][myFriends]) {
        cntNumOfFriends += 1;
        pairOfFriends += 1;
      }
      friendsGraph[me][myFriends] = friendsGraphOfFriends[me][myFriends];
    }
  }

  newFriendsNumOnDays.push(parseInt(pairOfFriends / 2));
}

let daysOfEveryoneBecomeFriends = newFriendsNumOnDays.length;
console.log(daysOfEveryoneBecomeFriends);
console.log(newFriendsNumOnDays.join('\n'));
