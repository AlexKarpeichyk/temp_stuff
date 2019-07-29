# coding: utf-8
# %load ../Solutions/3/test_FS

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
FE_lower_results = get_results(FE_lower)
FE_NUM_results = get_results(FE_NUM)
FE_puncstop_results = get_results(FE_puncstop)
FE_stem_results = get_results(FE_stem)

headers = ["cat","all","lower", "NUM", "puncstop", "stem"]

pd.set_option('precision',2)

df = pd.DataFrame(list(zip(prod_cats,
                           [FE_all_results[prod_cat] for prod_cat in prod_cats],
                           [FE_lower_results[prod_cat] for prod_cat in prod_cats],
                           [FE_NUM_results[prod_cat] for prod_cat in prod_cats],
                           [FE_puncstop_results[prod_cat] for prod_cat in prod_cats],
                           [FE_stem_results[prod_cat] for prod_cat in prod_cats])),
                  columns=headers)
display(df)
ax = df.plot.bar(x=0,title="Experimental Results")
ax.set_ylabel("Classifier Accuracy")
ax.set_xlabel("Product Category")
ax.set_ylim(0.5,1.0)
ax.legend(bbox_to_anchor=(0.95, 1))
