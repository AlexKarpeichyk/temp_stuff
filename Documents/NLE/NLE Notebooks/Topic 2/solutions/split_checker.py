# coding: utf-8
from random import sample # have a look at https://docs.python.org/3/library/random.html to see what random.sample does
from sussex_nltk.corpus_readers import AmazonReviewCorpusReader

 
def split_data(data, ratio=0.7): # when the second argument is not given, it defaults to 0.7
    """
    Given corpus generator and ratio:
     - partitions the corpus into training data and test data, where the proportion in train is ratio,

    :param data: A corpus generator.
    :param ratio: The proportion of training documents (default 0.7)
    :return: a pair (tuple) of lists where the first element of the 
            pair is a list of the training data and the second is a list of the test data.
    """
    
    data = list(data) # data is a generator, so this puts all the generated items in a list
 
    n = len(data)  #Found out number of samples present
    train_indices = sample(range(n), int(n * ratio))          #Randomly select training indices
    test_indices = list(set(range(n)) - set(train_indices))   #Randomly select testing indices
 
    train = [data[i] for i in train_indices]           #Use training indices to select data
    test = [data[i] for i in test_indices]             #Use testing indices to select data
 
    return (train, test)                       #Return split data
 
#Create an Amazon corpus reader pointing at only book reviews
book_reader = AmazonReviewCorpusReader().category("book")

for ratio in [0.1,0.5,0.9]:
    pos_train, pos_test = split_data(book_reader.positive().documents(),ratio)
    neg_train, neg_test = split_data(book_reader.negative().documents(),ratio)
    print(ratio,len(pos_train),len(pos_test))
