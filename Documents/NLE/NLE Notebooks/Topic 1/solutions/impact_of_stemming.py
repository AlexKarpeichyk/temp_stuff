# coding: utf-8
from sussex_nltk.corpus_readers import ReutersCorpusReader
from nltk.tokenize import word_tokenize
from nltk.stem.porter import PorterStemmer

def vocabulary_size(sentences):
    tok_counts = collections.defaultdict(int)
    for sentence in sentences: 
        for token in sentence:
            tok_counts[token] += 1
    return len(tok_counts.keys())

rcr = ReutersCorpusReader()    
st = PorterStemmer()

sample_size = 10000

raw_sentences = rcr.sample_raw_sents(sample_size)
tokenised_sentences = [word_tokenize(sentence) for sentence in raw_sentences]
stemmed_sentences = [[st.stem(token) for token in sentence] for sentence in tokenised_sentences]
raw_vocab_size = vocabulary_size(tokenised_sentences)
stemmed_vocab_size = vocabulary_size(stemmed_sentences)
print("Stemming produced a {0:.2f}% reduction in vocabulary size from {1} to {2}".format(
    100*(raw_vocab_size - stemmed_vocab_size)/raw_vocab_size,raw_vocab_size,stemmed_vocab_size))
