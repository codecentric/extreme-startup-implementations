'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/Prime Minister/)) {
    return next();
  }
  res.send('David Cameron');
}
