# coding: utf-8
reviews = dvd_reviews[:10]
for review in reviews:
    parsed_review = nlp(review)
    all_verbs = set()
    nsubj_and_dobj_pairs = collections.defaultdict(list)
    for token in parsed_review:
        if token.pos_ == 'VERB':
            all_verbs.add(token)
            for child_1 in token.children:
                if child_1.dep_ == 'nsubj':
                    for child_2 in token.children:
                        if child_2.dep_ == 'dobj':
                            nsubj_and_dobj_pairs[token].append((child_1,child_2))
                    break
    verbs = list(nsubj_and_dobj_pairs.keys())
    all_pairs = [nsubj_and_dobj_pairs[verb] for verb in verbs]
    print("Review:\n{}".format(review))
    df = pd.DataFrame(list(zip_longest(*all_pairs)),columns = verbs).applymap(lambda x: '' if x == None else x)
    display(df)
