# coding: utf-8
reviews = dvd_reviews[:10]
for review in reviews:
    parsed_review = nlp(review)
    all_verbs = set()
    verbs_with_nsubj = set()
    verbs_with_nsubj_and_dobj = set()
    for token in parsed_review:
        if token.pos_ == 'VERB':
            all_verbs.add(token)
            for child in token.children:
                if child.dep_ == 'nsubj':
                    verbs_with_nsubj.add(token)
                    for child in token.children:
                        if child.dep_ == 'dobj':
                            verbs_with_nsubj_and_dobj.add(token)
                    break
    print("Review:\n{}".format(review))
    df = pd.DataFrame([(verb,verb in verbs_with_nsubj,verb in verbs_with_nsubj_and_dobj) for verb in all_verbs],
                      columns=["verb",'has nsubj?','has nsubj & dobj'])
    df.loc[:, 'has nsubj?':'has nsubj & dobj'] = (df.loc[:, 'has nsubj?':'has nsubj & dobj']
                                       .applymap(lambda x: 'yes' if x else ''))
    display(df)
