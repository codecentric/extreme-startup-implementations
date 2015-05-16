from flask import Flask
from flask import request
import json
import re

app = Flask(__name__)

scores = '{"a": 1, "c": 3, "b": 3, "e": 1, "d": 2, "g": 2, "f": 4, "i": 1, "h": 4, "k": 5, "j": 8, "m": 3, "l": 1, "o": 1, "n": 1, "q": 10, "p": 3, "s": 1, "r": 1, "u": 1, "t": 1, "w": 4, "v": 4, "y": 4, "x": 8, "z": 10}'


def scrabble(word):
    decoded = json.loads(scores)
    val = 0
    for letter in word:
        val += int(decoded[letter])
    print(val)
    return str(val)


def math(string):
    count = string.count("plus")
    string = string.replace("plus", "+", count)
    count = string.count("multiplied by")
    string = string.replace("multiplied by", "*", count)
    count = string.count("minus")
    string = string.replace("minus", "-", count)
    string = string[8:]
    print(eval(string))
    return str(eval(string))


@app.route('/')
def hello_world():
    string = request.args.get('q')[10:]
    m = re.match("what is [1-9](.*)(.*)", string)
    print(string)
    if m:
        return math(string)
    m = re.match("^.*Prime Minister", string)
    if m:
        return "David Cameron"
    m = re.match("^.*played James Bond in the film Dr No", string)
    if m:
        return "Sean Connery"
    m = re.match("(.*)scrabble score of (.*)", string)
    if m:
        return scrabble(m.group(2))
    return ""


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
