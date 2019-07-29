# coding: utf-8
#%load ../Solutions/3/impact_of_normalisation

from sussex_nltk.corpus_readers import ReutersCorpusReader
from nltk.tokenize import word_tokenize

def vocabulary_size(sentences):
    tok_counts = collections.defaultdict(int)
    for sentence in sentences: 
        for token in sentence:
            tok_counts[token] += 1
    return len(tok_counts.keys())

rcr = ReutersCorpusReader()    

sample_size = 10000

raw_sentences = rcr.sample_raw_sents(sample_size)
tokenised_sentences = [word_tokenize(sentence) for sentence in raw_sentences]
lowered_sentences = [[token.lower() for token in sentence] for sentence in tokenised_sentences]
normalised_sentences = [["NUM" if token.isdigit() else token for token in sentence] for sentence in lowered_sentences]
raw_vocab_size = vocabulary_size(tokenised_sentences)
normalised_vocab_size = vocabulary_size(normalised_sentences)
print("Normalisation produced a {0:.2f}% reduction in vocabulary size from {1} to {2}".format(
    100*(raw_vocab_size - normalised_vocab_size)/raw_vocab_size,raw_vocab_size,normalised_vocab_size))
