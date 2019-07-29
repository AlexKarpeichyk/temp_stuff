# coding: utf-8
def known_vocabulary(training_data):
    vocab = set()
    for doc,c in training_data:
        for word in doc:
            vocab.add(word)
    return vocab
            
known_vocabulary(train_data)
