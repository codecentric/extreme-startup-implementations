'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/which of the following numbers are prime/)) {
    return next();
  }

  let numbers = JSON.parse('[' + req.query.q.split('primes: ')[1] + ']');
  res.send(numbers.filter(isPrime).join(', '));
}


function isPrime(n) {

   // If n is less than 2 or not an integer then by definition cannot be prime.
   if (n < 2) {
     return false;
   }
   if (n !== Math.round(n)) {
     return false;
   }

   for (let i = 2; i <= Math.sqrt(n); i++) {
      if (n % i === 0) {
        return false;
      }
   }

   return true;
}
