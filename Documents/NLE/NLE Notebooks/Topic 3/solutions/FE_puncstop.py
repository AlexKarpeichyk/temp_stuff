# coding: utf-8
def FE_puncstop(review):
    return [word for word in review.words() if word.isalpha() and word not in stopwords]
