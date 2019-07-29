# coding: utf-8
def add_exclamation(string):
    return string + "!"

for word in map(add_exclamation,dickens_words.split()):
    print(word)
