# coding: utf-8
from sussex_nltk.stats import evaluate_wordlist_classifier

experiments = [("Hand-Crafted lists",my_positive_word_list,my_negative_word_list),
               ("Top-k lists",top_pos,top_neg),
               ("Above-k lists",above_pos,above_neg)]


for description,pos_list,neg_list in experiments:
    #Create a new classifier with your words lists
    classifier = SimpleClassifier(pos_list, neg_list)
    score = evaluate_wordlist_classifier(classifier, pos_test, neg_test)
    print("The accuracy of the {0} classifer is {1:.2f}".format(description,score))
