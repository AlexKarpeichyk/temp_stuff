# coding: utf-8
from sussex_nltk.corpus_readers import ReutersCorpusReader
from sussex_nltk.tag import twitter_tag_batch
from nltk import pos_tag
from nltk.tokenize import word_tokenize
from classification_utils import *

def FE_all(review):
    return review.words()

def FE_tok(review):
    sentences = review.sents()
    tagged = [pos_tag(sentence) for sentence in sentences]
    return reduce(lambda words,review: words + [tok for tok,pos in review], tagged, [])

def FE_pos(review):
    sentences = review.sents()
    tagged = [pos_tag(sentence) for sentence in sentences]
    return reduce(lambda words,review: words + [pos for tok,pos in review], tagged, [])

def FE_tokpos(review):
    sentences = review.sents()
    tagged = [pos_tag(sentence) for sentence in sentences]
    return reduce(lambda words,review: words + [tok + "_" + pos for tok,pos in review], tagged, [])

def get_results(feature_extractor):
    results = {}
    for prod_cat in prod_cats:
        repetitions = 1 # accuracy figures are averaged over this many repetitions
        NB_accuracy_tot = 0
        for i in range(repetitions): # for each sample_size we will find average accuracy over several repetitions
            test, train   = get_formatted_train_test_data(prod_cat,feature_extractor)
            NB_accuracy_tot += run_NB_preformatted(train,test)
        results[prod_cat] = NB_accuracy_tot/repetitions
    return results

prod_cats = ["book","dvd","kitchen","electronics"]
FE_all_results = get_results(FE_all)
FE_tok_results = get_results(FE_tok)
FE_pos_results = get_results(FE_pos)
FE_tokpos_results = get_results(FE_tokpos)

headers = ["cat", "all", "tokens","pos", "tok+pos"]

pd.set_option('precision',2)

df = pd.DataFrame(list(zip(prod_cats,
                           [FE_all_results[prod_cat] for prod_cat in prod_cats],
                           [FE_tok_results[prod_cat] for prod_cat in prod_cats],
                           [FE_pos_results[prod_cat] for prod_cat in prod_cats],
                           [FE_tokpos_results[prod_cat] for prod_cat in prod_cats])),
                  columns=headers)
display(df)
ax = df.plot.bar(x=0,title="Experimental Results")
ax.set_ylabel("Classifier Accuracy")
ax.set_xlabel("Product Category")
ax.set_ylim(0.5,1.0)
ax.legend(bbox_to_anchor=(0.95, 1))
