# coding: utf-8
from collections import defaultdict
from sussex_nltk.corpus_readers import WSJCorpusReader

def simple_pos_ambiguity():
    """
    for each type in the Walls Street Journal corpus, this 
    function determines the number of different PoS tags that
    the type as been assigned.

    :param none
    :return: A dictionary (hashmap) mapping each type to its 
            degree of ambiguity (the number of distinct PoS tags 
            that the type is labelled with in the Wall Street 
            Journal Corpus).
    """
    wsj_reader = WSJCorpusReader()    #Create a new reader
    tags_dict = defaultdict(set)
    for tok,tag in wsj_reader.tagged_words():
        tags_dict[tok].add(tag)
    count_dict = defaultdict(int)
    for ty in tags_dict.keys():
        count_dict[ty] = len(tags_dict[ty])
    return count_dict
