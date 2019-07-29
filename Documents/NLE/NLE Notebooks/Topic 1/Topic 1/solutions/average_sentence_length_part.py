# coding: utf-8
from sussex_nltk.corpus_readers import MedlineCorpusReader
from sussex_nltk.corpus_readers import TwitterCorpusReader
from sussex_nltk.corpus_readers import ReutersCorpusReader

rcr = ReutersCorpusReader()    #Create a new reader
tcr = TwitterCorpusReader()    #Create a new reader
mcr = MedlineCorpusReader()    #Create a new reader

samplesize = 1000

#######################################

# To complete, add your code here

#######################################

# A Pandas dataframe is a convenient way to display the average sentence length (ASL) of each corpus in a table. 
 
# Create a dictionary.
# There is a key for each column - in this we have two columns 'Corpus' and 'ASL'
# The values of each key is a list of the values for each row of the corresponding column.
# The lists need to have the same length, corresponding to the number of rows in the table.

datadict = {'Corpus' : ['Reuters','Twitter','Medline'],
            'ASL' : [ASL_Reuters,ASL_Twitter,ASL_Medline]}

# Make a dataframe from the dictionary.
# The columns parameters allows us to specify the order of the columns.
# By default the columns would appear in alphabetical order of their key.

df = pd.DataFrame(datadict,columns=['Corpus','ASL'])

display(df)
