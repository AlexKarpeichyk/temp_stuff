# coding: utf-8
dickens_words = "It was the best of times, it was the worst of times"
dickens_counts = collections.defaultdict(int)
for word in dickens_words.split():
    dickens_counts[word] += 1
for word, count in dickens_counts.items():
    if count == 1:
        print('The word "{0}" occurred once.'.format(word))
    else:
        print('The word "{0}" occurred {1} times.'.format(word,count))
           
