# coding: utf-8
def get_vocabulary(wordlist):
    wordset = set()
    for word in wordlist:
        wordset.add(word)
    return wordset

testsent = "It was the best of times, it was the worst of times"
        
get_vocabulary(testsent.split())
