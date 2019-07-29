# coding: utf-8
from classification_utils import *

reader = AmazonReviewCorpusReader().category("kitchen")
word_list_size = 100
repetitions = 1 # accuracy figures are averaged over this many repetitions
sample_sizes = [1,10,50,100,200,400,600,700] #sample_size = number of the positive reviews =  number of negative reviews
WL_accuracies=[]
NB_accuracies=[]
for size in sample_sizes:
    WL_accuracy_tot = 0
    NB_accuracy_tot = 0
    for i in range(repetitions): # for each sample_size we will find average accuracy over several repetitions
        pos_train,neg_train,pos_test,neg_test = get_train_test_data(reader)
        pos_train_sample = sample(pos_train, size) 
        neg_train_sample = sample(neg_train, size) 
        WL_accuracy_tot += run_WL(pos_train_sample,neg_train_sample,pos_test,neg_test,word_list_size)
        NB_accuracy_tot += run_NB(pos_train_sample,neg_train_sample,pos_test,neg_test)
    WL_accuracies.append(WL_accuracy_tot/repetitions)
    NB_accuracies.append(NB_accuracy_tot/repetitions)

pd.set_option('precision',2)
df = pd.DataFrame(list(zip(sample_sizes, WL_accuracies, NB_accuracies)),
                  columns=["Sample size","WL accuracy","NB accuracy"])    
display(df)
ax = df.plot(title="Experimental Results",x=0)
ax.set_ylabel("Classifier Accuracy")
ax.set_ylim(0.4,1.0)
