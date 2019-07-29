# coding: utf-8
# %load ../Solutions/2/train_test_NB
from nltk.classify import NaiveBayesClassifier
from nltk.classify.util import accuracy

#Train the classifier on a list of reviews
#Note that the argument to the train method is the combined training data.
nb_classifier = NaiveBayesClassifier.train(formatted_train)

#Test on another list of reviews
print("The accuracy of the classifier is {0:.2f}".format(accuracy(nb_classifier, formatted_test)))
