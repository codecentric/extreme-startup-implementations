'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/Fibonacci sequence/)) {
    return next();
  }

  const match = req.query.q.match(/the (\d+).*/);
  res.send(fib(parseInt(match[1], 10)) + '');
}


function fib(numMax){
  let i = 0, j = 1, k = 0, x = 0;
  let numbers = [];
  for(; k < numMax; i = j, j = x, k++) {
      x = i + j;
      numbers.push(x);
  }
  return numbers[numbers.length - 2];
}
