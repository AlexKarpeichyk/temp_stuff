# coding: utf-8
def classify(doc,priors,c_probs,known_vocab):
    class_scores = collections.defaultdict(lambda:1)
    for c in priors.keys():
        class_scores[c] *= priors[c]
        for word in doc:
            if word in known_vocab:
                class_scores[c] *= c_probs[c][word]
    best_score = max(class_scores.values())
    return random.choice([c for c in class_scores.keys() if class_scores[c]== best_score])

c_priors = class_priors(train_data)
c_probs = cond_probs(train_data)
known_vocab = known_vocabulary(train_data)
sent = "looking really cloudy today"
doc = dict([(word, True) for word in sent.split()])
classify(doc,c_priors,c_probs,known_vocab)
