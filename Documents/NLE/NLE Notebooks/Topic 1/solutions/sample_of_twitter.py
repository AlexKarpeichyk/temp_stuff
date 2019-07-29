# coding: utf-8
# %load ../Solutions/3/sample_of_twitter

from sussex_nltk.corpus_readers import TwitterCorpusReader

tcr = TwitterCorpusReader()    #Create a new reader

sample_size = 20

for sentence in tcr.sample_raw_sents(sample_size): #get a sample of random sentences, where each sentence is a string
    print(sentence)
