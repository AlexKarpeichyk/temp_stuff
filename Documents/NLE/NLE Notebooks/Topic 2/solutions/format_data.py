# coding: utf-8
weather_data_train = [(dict([(word, True) for word in sent.split()]), "weather") for sent in weather_sents_train] 
football_data_train = [(dict([(word, True) for word in sent.split()]), "football") for sent in football_sents_train]
train_data = weather_data_train + football_data_train
