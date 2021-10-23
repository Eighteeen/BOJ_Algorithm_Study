const fs = require('fs');
const [speedLimit, speedOfCar] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `100
120`
).split('\n');

const getFineOfSpeed = (diffOfSpeed) => {
  let fineOfSpeed = 0;

  if (diffOfSpeed >= 1 && diffOfSpeed <= 20) {
    fineOfSpeed = 100;
  } else if (diffOfSpeed >= 11 && diffOfSpeed <= 30) {
    fineOfSpeed = 270;
  } else {
    fineOfSpeed = 500;
  }

  return fineOfSpeed;
};

const diffOfSpeed = speedOfCar - speedLimit;

diffOfSpeed <= 0
  ? console.log(`Congratulations, you are within the speed limit!`)
  : console.log(`You are speeding and your fine is $${getFineOfSpeed(diffOfSpeed)}.`);
//// 깔끔 :2