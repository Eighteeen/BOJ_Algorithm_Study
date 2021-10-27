const fs = require('fs');
const [numA, numB, numC] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5 3 8`)
  .split(' ')
  .map(Number);

const getOperator = (operandA, operandB, result) => {
  if (operandA + operandB === result) {
    return '+';
  }
  if (operandA - operandB === result) {
    return '-';
  }
  if (operandA * operandB === result) {
    return '*';
  }
  if (operandA / operandB === result && operandA % operandB === 0) {
    return '/';
  }
  return undefined;
};

let operator = getOperator(numA, numB, numC);
if (operator === undefined) {
  operator = getOperator(numB, numC, numA);
  console.log(`${numA}=${numB}${operator}${numC}`);
} else {
  console.log(`${numA}${operator}${numB}=${numC}`);
}
