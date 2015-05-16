import re

from flask import Flask
from flask import request

from shouldhavedone import my_math
from shouldhavedone import text


app = Flask(__name__)


def do_answer(return_method):
    answer = return_method
    print(answer)
    return answer



@app.route('/')
def hello_world():
    question = request.args.get('q')[10:]
    print(question)
    m = re.match("(.*)plus(.*)", question)
    if m:
        return do_answer(my_math.arithmetic(question))
    m = re.match("(.*)multiplied(.*)", question)
    if m:
        return do_answer(my_math.arithmetic(question))
    m = re.match("(.*)minus(.*)", question)
    if m:
        return do_answer(my_math.arithmetic(question))
    m = re.match("(.*)largest(.*)", question)
    if m:
        return do_answer(my_math.largest(question))
    m = re.match("(.*)square and(.*)", question)
    if m:
        return do_answer(my_math.square_cube(question))
    m = re.match("(.*)color(.*)", question)
    if m:
        return do_answer("yellow")
    m = re.match("(.*)Prime Minister(.*)", question)
    if m:
        return do_answer("David Cameron")
    m = re.match("(.*)Eiffel(.*)", question)
    if m:
        return do_answer("Paris")
    m = re.match("(.*)James Bond(.*)", question)
    if m:
        return do_answer("Sean Connery")
    m = re.match("(.*)Spain(.*)", question)
    if m:
        return do_answer("peseta")
    m = re.match("(.*)are primes(.*)", question)
    if m:
        return do_answer(my_math.which_are_primes(question))
    m = re.match("(.*)Fibonacci(.*)", question)
    if m:
        return do_answer(my_math.fibonnaci(question))
    m = re.match("(.*)power of(.*)", question)
    if m:
        return do_answer(my_math.power(question))
    m = re.match("^what is the english scrabble score of(.*)", question)
    if m:
        return do_answer(text.scrabble_score(question))
    m = re.match("^which of the following is an anagram of(.*)", question)
    if m:
        return do_answer(text.anagram(question))
    return ''


if __name__ == '__main__':
    app.run(host="0.0.0.0")
