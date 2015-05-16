from unittest import TestCase

__author__ = 'andi'

from shouldhavedone import my_math_lab


class TestArithmetic(TestCase):

    def test_arithmetic_5plus5(self):
        question = "what is 5 plus 5"
        self.assertEqual("10", my_math_lab.arithmetic(question))

    def test_arithmetic_23plus10(self):
        question = "what is 23 plus 10"
        self.assertEqual("33", my_math_lab.arithmetic(question))

    def test_arithmetic_7plus2(self):
        question = "what is 7 plus 2"
        self.assertEqual("9", my_math_lab.arithmetic(question))

    def test_largest_1_2_3_4(self):
        question = "which of the following numbers is the largest: 1, 2, 3, 4"
        self.assertEqual("4", my_math_lab.largest(question))

    def test_largest_25_107_140_51(self):
        question = "which of the following numbers is the largest: 25, 107, 140, 51"
        self.assertEqual("140", my_math_lab.largest(question))

    def test_arithmetic_9times2plus1(self):
        question = "what is 9 multiplied by 2 plus 1"
        self.assertEqual("19", my_math_lab.arithmetic(question))

    def test_arithmetic_5plus4times0(self):
        question = "what is 5 plus 4 multiplied by 0"
        self.assertEqual("5", my_math_lab.arithmetic(question))

    def test_square_cube_9_324_101_110(self):
        question = "which of the following numbers is both a square and a cube: 9, 324, 101, 110"
        self.assertEqual("", my_math_lab.square_cube(question))

    def test_square_cube_9_324_1000000_64(self):
        question = "which of the following numbers is both a square and a cube: 9, 1000000, 101, 64"
        self.assertEqual("1000000, 64", my_math_lab.square_cube(question))

    def test_which_is_prime_3_5_8_16_23(self):
        question = "which of the following numbers are primes: 3, 5, 8, 16, 23"
        self.assertEqual("3, 5, 23", my_math_lab.which_are_primes(question))

    def test_arithmetic_10plus11minus1times2(self):
        question = "what is 10 plus 11 minus 1 multiplied by 2"
        self.assertEqual("19", my_math_lab.arithmetic(question))

    def test_fibonnaci(self):
        question = "what is the 22rd number in the Fibonacci sequence"
        self.assertEqual("17711", my_math_lab.fibonnaci(question))

    def test_power(self):
        question = "what is 13 to the power of 11"
        self.assertEqual("1792160394037", my_math_lab.power(question))

