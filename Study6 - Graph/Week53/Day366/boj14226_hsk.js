const fs = require('fs');
const S = parseInt(process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `6`);

const getMinTimeToMakeEmoticon = () => {
  let minTime = timeCalcArr[S][1];
  for (let i = 1; i < S; i++) {
    if (timeCalcArr[S][i] !== TIME_INIT_VALUE) {
      minTime = Math.min(minTime, timeCalcArr[S][i]);
    }
  }
  return minTime;
};

const setMinTimeToMakeEmoticon = () => {
  let queue = [[1, 0]];
  timeCalcArr[1][0] = 0;

  while (queue.length) {
    const [screenCnt, clipboardCnt] = queue.shift();

    //// 욜 boolean 변수 이름으로 설명해줘서 이해하기 편했어요
    let isSave = timeCalcArr[screenCnt][screenCnt] === TIME_INIT_VALUE;
    if (isSave) {
      timeCalcArr[screenCnt][screenCnt] = timeCalcArr[screenCnt][clipboardCnt] + 1;
      queue.push([screenCnt, screenCnt]);
    }

    let isPaste =
      screenCnt + clipboardCnt <= S && timeCalcArr[screenCnt + clipboardCnt][clipboardCnt] === TIME_INIT_VALUE;
    if (isPaste) {
      timeCalcArr[screenCnt + clipboardCnt][clipboardCnt] = timeCalcArr[screenCnt][clipboardCnt] + 1;
      queue.push([screenCnt + clipboardCnt, clipboardCnt]);
    }

    let isRemove = screenCnt - 1 >= 0 && timeCalcArr[screenCnt - 1][clipboardCnt] === TIME_INIT_VALUE;
    if (isRemove) {
      timeCalcArr[screenCnt - 1][clipboardCnt] = timeCalcArr[screenCnt][clipboardCnt] + 1;
      queue.push([screenCnt - 1, clipboardCnt]);
    }
  }
};

const TIME_INIT_VALUE = -1;
const timeCalcArr = Array.from(new Array(S + 1), () => new Array(S + 1).fill(TIME_INIT_VALUE));
setMinTimeToMakeEmoticon();
console.log(getMinTimeToMakeEmoticon());
