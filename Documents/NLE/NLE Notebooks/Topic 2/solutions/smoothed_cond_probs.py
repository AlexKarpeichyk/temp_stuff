# coding: utf-8
def cond_probs(training_data):
    # c_probs will hold our conditional probabilities
    c_probs = collections.defaultdict(lambda: collections.defaultdict(float)) 
    # docs_with_word is a mapping from a class to a mapping from a word to number of documents of that category the word appeared in 
    docs_with_word = collections.defaultdict(lambda: collections.defaultdict(int)) 
    # tot_words is a mapping from a class to the total number of words documents of that class
    tot_words = collections.defaultdict(int)  
    
    # first get the counts of words in documents of a class and total word count per class
    for doc,c in training_data:
        for word in doc:
            docs_with_word[c][word] += 1
            tot_words[c] += 1

    # next, add the add-one smoothing counts
    known_vocab = known_vocabulary(training_data)
    for c in docs_with_word.keys():
        for word in known_vocab:
            docs_with_word[c][word] += 1
    # update tot_words to account for the additional (hallucinated) counts
        tot_words[c] += len(known_vocab)
    
    # now compute the conditional probabilities
    for c in docs_with_word.keys(): 
        for word in docs_with_word[c].keys():
            c_probs[c][word] = docs_with_word[c][word] / tot_words[c]
            
    return c_probs
        
c_ps = cond_probs(train_data)

c_ps["weather"]["city"]
