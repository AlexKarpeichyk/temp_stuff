# coding: utf-8
from classification_utils import * 

dvd_reader = AmazonReviewCorpusReader().category("dvd")
word_list_size = 100
repetitions = 5 # accuracy figures are averaged over this many repetitions
WL_accuracy_tot = 0
NB_accuracy_tot = 0
for i in range(repetitions): # for each sample_size we will find average accuracy over several repetitions
    pos_train,neg_train,pos_test,neg_test = get_train_test_data(dvd_reader)
    WL_accuracy_tot += run_WL(pos_train,neg_train,pos_test,neg_test,word_list_size)
    NB_accuracy_tot += run_NB(pos_train,neg_train,pos_test,neg_test)
WL_accuracy = WL_accuracy_tot/repetitions
NB_accuracy = NB_accuracy_tot/repetitions
df = pd.DataFrame([("Word List",WL_accuracy),("NB",NB_accuracy)])
display(df)
ax = df.plot.bar(title="Experimental Results",legend=False,x=0)
ax.set_ylabel("Classifier Accuracy")
ax.set_xlabel("Classifier")
ax.set_ylim(0.5,1.0)
