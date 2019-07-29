# coding: utf-8
from sussex_nltk.corpus_readers import ReutersCorpusReader
from nltk.tokenize import word_tokenize
from nltk.stem.porter import PorterStemmer

rcr = ReutersCorpusReader() 
st = PorterStemmer()

sample_size = 10

raw_sentences = rcr.sample_raw_sents(sample_size)
tokenised_sentences = [word_tokenize(sentence) for sentence in raw_sentences]

for sentence in tokenised_sentences:
    df = pd.DataFrame(list(zip_longest(sentence,[st.stem(token) for token in sentence])),columns=["BEFORE","AFTER"])
    print(df)
