# coding: utf-8
from nltk.probability import FreqDist # see http://www.nltk.org/api/nltk.html#module-nltk.probability
from sussex_nltk.corpus_readers import AmazonReviewCorpusReader
from functools import reduce # see https://docs.python.org/3/library/functools.html
from nltk.corpus import stopwords

stopwords = stopwords.words('english')

def remove_stopwords_and_punctuation(words):
    return [w for w in words if w.isalpha() and w not in stopwords]

#Helper function. Given a list of reviews, return a list of all the words in those reviews
#To understand this look at the description of functools.reduce in https://docs.python.org/3/library/functools.html
def get_all_words(amazon_reviews):
    return reduce(lambda words,review: words + review.words(), amazon_reviews, [])

#A frequency distribution over all words in positive book reviews
pos_freqdist = FreqDist(remove_stopwords_and_punctuation(get_all_words(pos_train)))
neg_freqdist = FreqDist(remove_stopwords_and_punctuation(get_all_words(neg_train)))

def most_frequent_words(freqdist,k):
    return [word for word,count in freqdist.most_common(k)]

def words_above_threshold(freqdist,k):
    return [word for word in freqdist if freqdist[word]>k]


top_pos = most_frequent_words(pos_book_freqdist,100)
top_neg = most_frequent_words(neg_book_freqdist,100)
above_pos = words_above_threshold(pos_freqdist,100)
above_neg = words_above_threshold(neg_freqdist,100)
display(pd.DataFrame(list(zip_longest(top_pos,top_neg,above_pos,above_neg)),columns=["TopPos","TopNeg","AbovePos","AboveNeg"]))
