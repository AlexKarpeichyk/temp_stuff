# coding: utf-8
def FE_NUM(review):
    return ["NUM" if word.isdigit() else word for word in review.words()]
