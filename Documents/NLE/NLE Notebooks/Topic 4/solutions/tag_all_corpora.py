# coding: utf-8
from sussex_nltk.corpus_readers import ReutersCorpusReader, MedlineCorpusReader, TwitterCorpusReader
from sussex_nltk.tag import twitter_tag_batch
from nltk import pos_tag
from nltk.tokenize import word_tokenize

number_of_sentences = 10     #Number of sentences to sample and display
rcr = ReutersCorpusReader()  #Create a corpus reader
mcr = MedlineCorpusReader()
tcr = TwitterCorpusReader()

reuters_sents = rcr.sample_raw_sents(number_of_sentences) 
medline_sents = mcr.sample_raw_sents(number_of_sentences) 
twitter_sents = tcr.sample_raw_sents(number_of_sentences) 

#Tag with twitter specific tagger
# - it also tokenises for you in a twitter specific way
twitter_tagged_reuters = twitter_tag_batch(reuters_sents)   
twitter_tagged_medline = twitter_tag_batch(medline_sents)   
twitter_tagged_twitter = twitter_tag_batch(twitter_sents)   

#Tag with NLTK's maximum entropy tagger         
nltk_tagged_reuters = [pos_tag(word_tokenize(sentence)) for sentence in reuters_sents]  
nltk_tagged_medline = [pos_tag(word_tokenize(sentence)) for sentence in medline_sents]  
nltk_tagged_twitter = [pos_tag(word_tokenize(sentence)) for sentence in twitter_sents]  

#Print each sentence
print("-----------------------------------------")
print("Reuters Sample")
print("-----------------------------------------")
for raw, twitter_sentence, nltk_sentence in zip(reuters_sents,twitter_tagged_reuters,nltk_tagged_reuters):
    print("\n",raw,"\n")
    df = pd.DataFrame(list(zip_longest([(token,tag) for token,tag in nltk_sentence],
                                       [(token,tag) for token,tag in twitter_sentence])),
                      columns=["nltk tagger","twitter tagger"])
    print(df)
print("-----------------------------------------")
print("Medline Sample")
print("-----------------------------------------")
for raw, twitter_sentence, nltk_sentence in zip(medline_sents,twitter_tagged_medline,nltk_tagged_medline):
    print("\n",raw,"\n")
    df = pd.DataFrame(list(zip_longest([(token,tag) for token,tag in nltk_sentence],
                                       [(token,tag) for token,tag in twitter_sentence])),
                      columns=["nltk tagger","twitter tagger"])
    print(df)
print("-----------------------------------------")
print("Twitter Sample")
print("-----------------------------------------")
for raw, twitter_sentence, nltk_sentence in zip(twitter_sents,twitter_tagged_twitter,nltk_tagged_twitter):
    print("\n",raw,"\n")
    df = pd.DataFrame(list(zip_longest([(token,tag) for token,tag in nltk_sentence],
                                       [(token,tag) for token,tag in twitter_sentence])),
                      columns=["nltk tagger","twitter tagger"])
    print(df)
