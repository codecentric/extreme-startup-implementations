/*eslint-env mocha */

'use strict';

import request from 'supertest';
import fs from 'fs';
import {join} from 'path';
import app from '../app';

describe('questions', () => {

  fs.readFileSync(
    join(__dirname, './_simple_questions.txt'),
    {encoding: 'utf8'}
  )
  .split('\n')
  .filter(line => line.trim().length > 0)
  .forEach(line => {
    const [question, answer] = line.split('!!!');

    it(question, (done) => {
      request(app)
        .get('/')
        .query({q: question})
        .expect(200)
        .expect(answer)
        .end(done);
    });

  });

});
