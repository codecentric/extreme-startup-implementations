'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/which of the following numbers is the largest/)) {
    return next();
  }

  let numbers = '[' + req.query.q.split('largest: ')[1] + ']';
  res.send(JSON.parse(numbers).reduce((r, n) => Math.max(r, n)) + '');
}
