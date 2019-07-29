# coding: utf-8
def count_sentences_per_paragraph(input_text):
    """
    Given an input text:
     - assign a number to each paragraph,
     - count the number of sentences in each paragraph,
     - output a list of all paragraph numbers together
       with the number of senfor a,b in enumerate(['The','Holy','Grail']): print a,btences in it.

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
    sentence_counts = []
    for paragraph in paragraphs:
        number_of_sentences = count_sentences(paragraph)
        sentence_counts.append(number_of_sentences)
    sentences_per_paragraph = [(ind,val) for ind,val in enumerate(sentence_counts, 1)]
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
