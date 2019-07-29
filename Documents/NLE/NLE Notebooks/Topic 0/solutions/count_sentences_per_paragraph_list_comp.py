# coding: utf-8
def count_sentences_per_paragraph(input_text):
    """
    Given an input text:
     - assign a number to each paragraph,
     - count the number of sentences in each paragraph,
     - output a list of all paragraph numbers together
       with the number of sentences in it.

    :param input_text: A character string possibly containing
                        periods "." to separate sentences and
                        paragraph marks "\n" to separate
                        paragraphs.
    :return: A list of ordered pairs (tuples) where the first
            element of the pair is the paragraph number and
            the second element is the number of sentences in
            that paragraph.
            Sample output: [(0, 1), (1, 3), (2, 3), (3, 1)]
    """
    
    paragraphs = input_text.split("\n")
    
    # Apply the count_sentences function to every element of paragraphs,
    # return the results in a new list, call it sentence_counts:
    
    sentence_counts = [count_sentences(paragraph) for paragraph in paragraphs]
    paragraph_numbers = range(len(paragraphs))
    sentences_per_paragraph = zip(paragraph_numbers, sentence_counts)
    return sentences_per_paragraph

def count_sentences(paragraph):
    """
    A sentence is a character string delimited by a period "."
    Given an input paragraph, return the number of sentences
    in it.
    :param paragraph: Character string with sentences.
    :return: number of sentences in the input paragraph
    """
    
    sentences = paragraph.split(".")
    return len(sentences)

for para, count in count_sentences_per_paragraph(sample_text):
    print("paragraph {0} contains {1} sentence(s)".format(para,count))
