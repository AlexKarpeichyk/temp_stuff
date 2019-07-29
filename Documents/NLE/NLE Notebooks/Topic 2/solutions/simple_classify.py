# coding: utf-8
from nltk.classify.api import ClassifierI
import random

class SimpleClassifier(ClassifierI): 

    def __init__(self, pos, neg): 
        self._pos = pos 
        self._neg = neg 

    def classify(self, words): 
        score = 0
        
        # add code here that assigns an appropriate value to score
        for word in words:
            if word in self._pos:
                score += 1
            if word in self._neg:
                score -= 1
        if score < 0:
            return "N"
        if score > 0:
            return "P"
        return random.choice(["N","P"])

    def batch_classify(self, docs): 
        return [self.classify(doc.words() if hasattr(doc, 'words') else doc) for doc in docs] 

    def labels(self): 
        return ("P", "N")

#Example usage:

classifier = SimpleClassifier(top_pos, top_neg)
classifier.classify("I read the book".split())
