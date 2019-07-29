# coding: utf-8
from sussex_nltk.corpus_readers import MedlineCorpusReader
from sussex_nltk.corpus_readers import TwitterCorpusReader
from sussex_nltk.corpus_readers import ReutersCorpusReader

rcr = ReutersCorpusReader()    #Create a new reader
tcr = TwitterCorpusReader()    #Create a new reader
mcr = MedlineCorpusReader()    #Create a new reader

samplesize = 10

for sentence in rcr.sample_raw_sents(samplesize): 
    print(tokenise(sentence))
for sentence in tcr.sample_raw_sents(samplesize): 
    print(tokenise(sentence))
for sentence in mcr.sample_raw_sents(samplesize): 
    print(tokenise(sentence))
