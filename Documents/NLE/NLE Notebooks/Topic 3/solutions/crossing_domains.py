# coding: utf-8
from sussex_nltk.corpus_readers import AmazonReviewCorpusReader
from classification_utils import *

dvd_test, dvd_train   = get_formatted_train_test_data("dvd")
book_test, book_train = get_formatted_train_test_data("book")
kitchen_test, kitchen_train  = get_formatted_train_test_data("kitchen")
electronics_test, electronics_train = get_formatted_train_test_data("electronics") 

sources = {"dvd" : dvd_train, 
           "book" : book_train,
           "kitchen" : kitchen_train,
           "electronics" : electronics_train
         }
targets = {"dvd" : dvd_test, 
           "book" : book_test,
           "kitchen" : kitchen_test,
           "electronics" : electronics_test
         }

results = []
for source in sources.keys():
    for target in targets.keys():
        results.append((source,target,run_NB_preformatted(sources[source],targets[target])))
pd.set_option('precision',2)
df = pd.DataFrame(results,columns=["Source","Target","Accuracy"])   
display(df)
ax = df.set_index(['Source', 'Target']).plot.bar(legend=False,title="Experimental Results")
ax.set_ylabel("Classifier Accuracy")
ax.set_ylim(0.5,1.0)
