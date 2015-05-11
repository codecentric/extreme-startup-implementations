'use strict';

import express from 'express';
import bodyParser from 'body-parser';
import multer from 'multer';

// we define handlers using this array to get a predictable order of handlers
const handlers = [
  'whatIsMyName',
  'largest',
  'square_and_cube',
  'banana',
  'paris',
  'drno',
  'spain',
  'primes',
  'primeMinister',
  'fib',
  'scrabble',
  'anagram',
  'math'
];

const app = express();
export default app;

 // for parsing application/json
app.use(bodyParser.json());
// for parsing application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({extended: true}));
// for parsing multipart/form-data
app.use(multer());

// log all requests so that we can inspect them
app.use((req, res, next) => {
  // ensure that there is always a q query parameter
  req.query.q = req.query.q !== undefined ? req.query.q : '';

  if (process.env.TEST !== 'true') {
    console.log(req.method, req.url, '\nQuestion:', req.query.q);
  }

  next();
});

// register all handlers in order as express middleware.
// This is the easiest way as express middleware can call `next()`!
handlers.forEach(handler => {
  app.use(require('./handlers/' + handler));
});

// when we reach this handler, then no previous handler was able to
// serve the request. This may be a new question. Interesting!
app.use((req, res) => {
  res.status(400).send('Sorry, cannot handle this!');
});
