# coding: utf-8
def class_priors(data):
    doc_counts = collections.defaultdict(int)
    priors = collections.defaultdict(float)
    # first we get the document count for each class
    for doc,c in data:
        doc_counts[c] += 1
    # now we add counts to achieve add-one smoothing
    for c in doc_counts:
        doc_counts[c] += 1
    # now we compute the probabilities 
    # we must add len(doc_counts) to the denominator because of the add-one smoothing
    for c in doc_counts.keys():
        priors[c] = doc_counts[c]/(len(data)+len(doc_counts)) 
    return priors

priors = class_priors(train_data)
print("The prior for class 'football' is {0:.3f}.\nThe prior for class 'weather' is {1:.3f}.\nThe priors sum to {2:.3f}".
      format(priors["football"],priors["weather"],priors["football"] + priors["weather"]))
