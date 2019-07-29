# coding: utf-8
address_book = {"Harold Wilson" : (1974,1976,"Labour"),
                "James Callaghan" : (1976,1979,"Labour"),
                "Margaret Thatcher" : (1979,1990,"Conservative"),
                "John Major" : (1990,1997,"Conservative"),
                "Tony Blair" : (1997,2007,"Labour"),
                "Gordon Brown" : (2007,2010,"Labour"),
                "David Cameron" : (2010,2016,"Conservative")            
               }

for name, info in address_book.items():
    print("{0} was the British prime minister from {1} to {2} and was a member of the {3} party.".
          format(name,info[0],info[1],info[2]))
