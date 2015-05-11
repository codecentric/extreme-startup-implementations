'use strict';

const regex = /which of the following numbers is both a square and a cube/;

export default function(req, res, next) {
  if (!req.query.q.match(regex)) {
    return next();
  }

  let numbers = '[' + req.query.q.split('and a cube: ')[1] + ']';
  res.send(JSON.parse(numbers).filter(n => {
    const result = Math.abs(Math.round(Math.sqrt(n)) - Math.sqrt(n)) < 0.1 &&
      Math.abs(Math.round(Math.pow(n, 1 / 3)) - Math.pow(n, 1 / 3)) < 0.1;
    return result;
  }).join(', '));
}
