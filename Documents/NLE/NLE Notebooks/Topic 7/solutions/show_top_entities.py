# coding: utf-8
from pylab import rcParams

def locations(target_token, document,num_bins): 
    list_of_indices = [ent.root.i for ent in document.ents if ent.string.strip().lower() == target_token]
    return pd.Series(np.histogram(list_of_indices, bins=num_bins)[0])
    
def named_entity_counts(document,label):
    occurrences = [ent.string.strip().lower() for ent in document.ents 
                   if ent.label_ == label and ent.string.strip()]
    return Counter(occurrences)

def show_top_entities(entity_label,document,k,num_bins):
    rcParams['figure.figsize'] = 16, k*2
    top_people_counts = named_entity_counts(document,entity_label).most_common(k)
    top_people = {person for person,count in top_people_counts}
    pd.DataFrame(
    {name: locations(name.lower(),document,num_bins) 
     for name in top_people}).plot(subplots=True)

text = parsed_emma
number_of_people = 20
number_of_bins = 100
show_top_entities("PERSON",text,number_of_people,number_of_bins)
