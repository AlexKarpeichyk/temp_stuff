# coding: utf-8
from nltk.stem.porter import PorterStemmer

stemmer = PorterStemmer() #Create a new stemmer

def FE_stem(review):
    return [stemmer.stem(word) for word in review.words()]
