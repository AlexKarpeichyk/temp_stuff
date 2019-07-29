# coding: utf-8
from classification_utils import *

prod_cats = ["book","dvd","kitchen","electronics"]
FE_all_results = {}
for prod_cat in prod_cats:
    repetitions = 2 # accuracy figures are averaged over this many repetitions
    NB_accuracy_tot = 0
    for i in range(repetitions): # for each sample_size we will find average accuracy over several repetitions
        test, train   = get_formatted_train_test_data(prod_cat,FE_all)
        NB_accuracy_tot += run_NB_preformatted(train,test)
    FE_all_results[prod_cat] = NB_accuracy_tot/repetitions
    
pd.set_option('precision',2)
df = pd.DataFrame.from_dict(FE_all_results,orient='index')
display(df)
ax = df.plot.bar(title="Experimental Results",legend=False)
ax.set_ylabel("Classifier Accuracy")
ax.set_xlabel("Product Category")
ax.set_ylim(0.5,1.0)
