# coding: utf-8
target_verbs = ["love","buy","want"]
direct_objects = collections.defaultdict(list) # this will be used to hold dobj lists for each target verb
for review in dvd_reviews:
    parsed_review = nlp(review)
    for token in parsed_review:
        if token.pos_ == 'VERB' and token.lemma_ in target_verbs:
            for child in token.children:
                if child.dep_ == 'dobj':
                    direct_objects[token.lemma_].append(child.lemma_) 
                    break
all_direct_objects = [direct_objects[verb] for verb in target_verbs]
df = pd.DataFrame(list(zip_longest(*all_direct_objects)),
                columns = target_verbs).applymap(lambda x: '' if x == None else x)
display(df)
