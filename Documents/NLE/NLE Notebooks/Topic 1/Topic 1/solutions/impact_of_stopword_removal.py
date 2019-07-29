# coding: utf-8
from sussex_nltk.corpus_readers import MedlineCorpusReader
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords

def vocabulary_size(sentences):
    tok_counts = collections.defaultdict(int)
    for sentence in sentences: 
        for token in sentence:
            tok_counts[token] += 1
    return len(tok_counts.keys())

mcr = MedlineCorpusReader()    
stopwords = stopwords.words('english')

sample_size = 10000

raw_sentences = rcr.sample_raw_sents(sample_size)
tokenised_sentences = [word_tokenize(sentence) for sentence in raw_sentences]

############################################
num_stopwords = 0
num_tokens = 0
for sentence in tokenised_sentences:
    for token in sentence:
        num_tokens += 1
        if token in stopwords:
            num_stopwords += 1
############################################

print("Stopword removal produced a {0:.2f}% reduction in number of tokens from {1} to {2}".format(
    100*(num_tokens - num_stopwords)/num_tokens,num_tokens,num_stopwords))
