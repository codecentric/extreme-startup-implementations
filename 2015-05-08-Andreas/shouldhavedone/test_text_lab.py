from unittest import TestCase
from shouldhavedone import text_lab
__author__ = 'andi'


# what is the english scrabble score of cloud
class TestScrabble_score(TestCase):
    def test_scrabble_score(self):
        question = "what is the english scrabble score of cloud"
        self.assertEqual("8", text_lab.scrabble_score(question))

    def test_anagram(self):
        question = """which of the following is an anagram of "listen": google, enlists, banana, silent, silent"""
        self.assertEquals("silent, silent", text_lab.anagram(question))