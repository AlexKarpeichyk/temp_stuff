# coding: utf-8
def print_word_counts(filepath):
    input_file_path = filepath
    input_file = open(input_file_path)
    input_text = input_file.read()
    word_counts = collections.defaultdict(int)
    for word in input_text.split():
        word_counts[word] += 1
    for word, count in word_counts.items():
        if count == 1:
            print('The word "{0}" occurred once.'.format(word))
        else:
            print('The word "{0}" occurred {1} times.'.format(word,count))
           

print_word_counts("/Users/davidw/Documents/teach/NLE/NLE Notebooks/Topic 0/sample_text.txt")
