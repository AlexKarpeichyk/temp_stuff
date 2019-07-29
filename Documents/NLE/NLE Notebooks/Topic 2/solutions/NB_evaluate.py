# coding: utf-8
def NB_evaluate(test_data,priors,c_probs,known_vocab):
    num_correct = 0
    for doc,c in test_data:
        predicted_class = classify(doc,priors,c_probs,known_vocab)
        if predicted_class == c:
            num_correct += 1
    return num_correct/len(test_data)

NB_evaluate(test_data,priors,c_probs,known_vocab)
