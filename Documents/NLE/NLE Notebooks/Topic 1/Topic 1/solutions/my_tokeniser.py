# coding: utf-8
import re    #import regex module

def tokenise(sentence):
    sentence = re.sub("'(s|m|(re)|(ve)|(ll)|(d))\s", " '\g<1> ",sentence + " ")
    sentence = re.sub("s'\s", "s ' ",sentence)
    sentence = re.sub("n't\s", " n't ",sentence)
    sentence = re.sub("gonna", "gon na",sentence)
    sentence = re.sub("\"(.+?)\"", "`` \g<1> ''",sentence)   
    sentence = re.sub("([.,?!])", " \g<1> ", sentence)
    return sentence.split()

testsentence = "After saying \"I won't help, I'm gonna leave!\", on his parents' arrival, the boy's behaviour improved."

print(tokenise(testsentence))
