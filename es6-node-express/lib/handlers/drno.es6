'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/ho played James Bond in the film Dr No/)) {
    return next();
  }
  res.send('Sean Connery');
}
