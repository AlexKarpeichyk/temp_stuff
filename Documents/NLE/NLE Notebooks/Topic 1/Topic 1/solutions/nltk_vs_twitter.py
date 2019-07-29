# coding: utf-8
#%load ../Solutions/3/nltk_vs_twitter

from sussex_nltk.tokenize import twitter_tokenize,twitter_tokenize_batch  #import CMU tokenize functions
from sussex_nltk.corpus_readers import TwitterCorpusReader

tcr = TwitterCorpusReader()

samplesize = 10

for sentence in tcr.sample_raw_sents(samplesize): 
    nltk_toks = word_tokenize(sentence)
    twit_toks = twitter_tokenize(sentence)
    display(pd.DataFrame(list(zip_longest(nltk_toks,twit_toks)),columns=["NLTK","TWIT"]))
