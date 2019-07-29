# coding: utf-8
def check_expectations(word_list,freqdist1,freqdist2,headers):
    match_freq = [freqdist1[word] for word in word_list]
    mismatch_freq = [freqdist2[word] for word in word_list]
    as_expected = [match_freq[i]>mismatch_freq[i] for i in range(len(word_list))]
    headers.append('Expected?')
    datadict = {headers[0] : word_list,
                headers[1] : match_freq,
                headers[2] : mismatch_freq,
                headers[3] : as_expected}
    df = pd.DataFrame(datadict,columns=headers)
    display(df,"\n")
