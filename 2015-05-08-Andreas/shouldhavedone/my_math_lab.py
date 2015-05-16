__author__ = 'andi'
import math
import re


def arithmetic(question):
    question = question[8:]
    question = question.replace("plus", "+").replace("multiplied by", "*").replace("minus", "-")
    return str(eval(question))


def largest(question):
    question = question[47:]
    question = question.replace(" ", "")
    number_list = str.split(question, ",")
    number_list = list(map(int, number_list))
    max_val = max(number_list)
    return str(max_val)


def square_cube(question):
    question = question[60:]
    number_list = question.replace(" ", "")
    number_list = number_list.split(",")
    list(map(int, number_list))
    return_values = []
    for number in number_list:
        if __is_square(number) and __is_third_square(number):
            return_values.append(number)
    return_value = ", ".join(return_values)
    return return_value


def __is_square(number):
    number = int(number)
    root = math.sqrt(number)
    if (root % 1) > 0:
        return False
    else:
        return True


def __is_third_square(number):
    number = int(number)
    float_root = (number ** (1.0 / 3.0))
    int_root = int(round(float_root))
    return float_equals(float_root, int_root)


def float_equals(a, b, tolerance=0.00000001):
    return abs(a-b) < tolerance

primes_under_100 = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]


def __is_prime(n):
    n = int(n)
    if n <= 100:
        return n in primes_under_100
    if n % 2 == 0 or n % 3 == 0:
        return False

    for f in range(5, int(n ** .5), 6):
        if n % f == 0 or n % (f + 2) == 0:
            return False
    return True


def which_are_primes(question):
    question = question[43:]
    question = question.replace(" ", "")
    number_list = question.split(",")
    number_list = list(map(int, number_list))
    result_list = []
    for number in number_list:
        if __is_prime(number):
            result_list.append(number)
    result_list = list(map(str, result_list))
    return ", ".join(result_list)


def fibonnaci(question):
    m = re.match("^what is the ([0-9]*).*", question)
    if m:
        number = int(m.group(1))
        return str(__calc_fibonnaci(number))
    return question


def __calc_fibonnaci(number):
    a, b = 0, 1
    for i in range(number):
        a, b = b, a + b
    return a


# what is 13 to the power of 11
def power(question):
    m = re.match("^.* ([0-9]*) to the power of ([0-9]*)", question)
    if m:
        x = int(m.group(1))
        y = int(m.group(2))
        return str(int(math.pow(x, y)))



