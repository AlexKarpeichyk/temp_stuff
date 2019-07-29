# coding: utf-8
def class_priors(data):
    doc_counts = collections.defaultdict(int)
    priors = collections.defaultdict(float)
    for doc,c in data:
        doc_counts[c] += 1
    for c in doc_counts.keys():
        priors[c] = doc_counts[c]/len(data)
    return priors

priors = class_priors(train_data)
priors["football"]
