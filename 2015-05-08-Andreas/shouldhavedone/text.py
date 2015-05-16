__author__ = 'andi'


__scrabble_letters = {"a": 1, "c": 3, "b": 3, "e": 1, "d": 2, "g": 2,
                      "f": 4, "i": 1, "h": 4, "k": 5, "j": 8, "m": 3,
                      "l": 1, "o": 1, "n": 1, "q": 10, "p": 3, "s": 1,
                      "r": 1, "u": 1, "t": 1, "w": 4, "v": 4, "y": 4,
                      "x": 8, "z": 10}


# what is the english scrabble score of cloud
def scrabble_score(question):
    word = question[38:]
    score = 0
    for letter in word:
        score += __scrabble_letters[letter]
    return str(score)


def __is_anagram(word1, word2):
    if len(word1) != len(word2):
        return False
    else:
        for letter in word1:
            if word1.count(letter) != word2.count(letter):
                return False
    return True


# which of the following is an anagram of "listen": google, enlists, banana, silent
def anagram(question):
    terms = question[40:]
    single_word = terms.split(":")[0].replace("\"", "")
    word_list = terms.split(":")[1].strip().split(", ")
    anagram_list = list()
    for word in word_list:
        if __is_anagram(single_word, word):
            anagram_list.append(word)
    return ", ".join(anagram_list)
