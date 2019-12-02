
#How to work with it
In order to apply the algorithm onto other database, we need few things:
1. Download the database as csv file statically (into the project folder).
2. Manually get the number of features expected per record inside the database and feed the
constructor of the Incognito with it.
3. Arrange a list of indexes of the selected QIs appear in the record.
 (the record itself is a row in the database, for example: if a record is (id,age,overall) and the QIs: age,overall, we shall pass a list contain : {1,2})
4. Implement your own buildRep function which includes: 
 Choosing your generalizations (build the hash-maps correspond to the generalization)
5. Statically have the library "guru.nidi:graphviz-java:0.8.3"