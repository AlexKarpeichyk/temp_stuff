# %load solutions/verb_contexts

def get_interesting_contexts(novels,rels,num_characters):
    
    def of_interest(ent,rels,main_characters):
        return (ent.text.strip().lower() in main_characters 
                and ent.label_ == 'PERSON' 
                and ent.root.head.pos_ == 'VERB'
                and ent.root.dep_ in rels)  

    contexts = defaultdict(Counter)    
    for parsed_novel in novels:
        main_characters = get_main_characters(parsed_novel,num_characters)
        for ent in parsed_novel.ents:
            if of_interest(ent,rels,main_characters):
                contexts[ent.text.strip().lower()][ent.root.head.lemma_] += 1
    return contexts

novels = {parsed_novel}
number_of_characters_per_text = 8
target_rels = {'nsubj'} # use set to allow for the possibility of several target dependency relations
target_contexts = get_interesting_contexts(novels,target_rels,number_of_characters_per_text)
display(pd.DataFrame.from_dict(target_contexts).applymap(lambda x: '' if math.isnan(x) else x))
