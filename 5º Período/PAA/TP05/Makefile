CPP=g++
CFLAGS=
DEPS = AdjacencyMatrix.h
OBJ = AdjacencyMatrix.cpp main.cpp

%.o: %.cpp $(DEPS)
	$(CPP) -c -o $@ $< $(CFLAGS)

main: $(OBJ)
		g++ -o $@ $^ $(CFLAGS)
