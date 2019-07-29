# coding: utf-8
def tokenise(sentence):
    return re.sub("([.?!'])", " \g<1>", sentence).split()

print(tokenise(' What is the    air-speed . velocity of  an unladen swallow?   '))
