'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/what currency did Spain use before the Euro/)) {
    return next();
  }
  res.send('Peseta');
}
