# coding: utf-8
# %load ../Solutions/3/nltk_vs_mine

from nltk.tokenize import word_tokenize
from sussex_nltk.corpus_readers import ReutersCorpusReader

rcr = ReutersCorpusReader()    #Create a new reader

samplesize = 10   

for sentence in rcr.sample_raw_sents(samplesize): 
    nltk_toks = word_tokenize(sentence)
    my_toks = tokenise(sentence)
    display(pd.DataFrame(list(zip_longest(nltk_toks,my_toks)),columns=["NLTK","MINE"]))
