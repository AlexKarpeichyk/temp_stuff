# coding: utf-8
#%load ../Solutions/3/normalise_to_Nth

tokens = ["The", "1st", "and", "2nd", "placed", "runners", "lapped", "the", "5th","."]
print(["Nth" if (token.endswith(("nd","st","th")) and token[:-2].isdigit()) else token for token in tokens])  
