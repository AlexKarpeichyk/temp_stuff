# coding: utf-8
# partial solution
def cond_probs(training_data):
    # c_probs holds our conditional probabilities
    c_probs = collections.defaultdict(lambda: collections.defaultdict(float)) 
    # docs_with_word is a mapping from a class to a mapping from a word to number of documents of that category the word appeared in 
    docs_with_word = collections.defaultdict(lambda: collections.defaultdict(int)) 
    # tot_words is a mapping from a class to the total number of words documents of that class
    tot_words = collections.defaultdict(int)  
    
    # first get the counts of words in documents of a class and total word count per class

#    <put your code here>          

    # now compute the conditional probabilities

#    <your code here>
    
    return c_probs
        
c_ps = cond_probs(train_data)

c_ps["weather"]["today"]
