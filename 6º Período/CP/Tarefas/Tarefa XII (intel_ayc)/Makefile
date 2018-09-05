# sample Makefile.
# It compiles every .cpp files in the src/ directory to object files in obj/ directory, and build the ./run executable.
# It automatically handles include dependencies.

# You can modify it as you want (add libraries, compiler flags...).
# But if you want it to properly run on our benchmark service, don't rename or delete variables.

# using icc :
#COMPILER ?= $(ICC_PATH)icpc
# using gcc :
COMPILER ?= $(GCC_PATH)g++

# using icc :
#FLAGS ?= -std=c++0x -U__GXX_EXPERIMENTAL_COMPILER0X__ -xHOST -fast -w1 $(ICC_SUPPFLAGS)
# using gcc :
FLAGS ?= -O3 -fopenmp  $(GCC_SUPPFLAGS)

LDFLAGS ?= -O3 -fopenmp -pg 
LDLIBS = -lgomp
#example if using Intel® Threading Building Blocks :
#LDLIBS = -ltbb -ltbbmalloc

EXECUTABLE = run

TEAM_ID = # put your 32chars team id here and you will be able to submit your program from command line using "make submit" 

SRCS=$(wildcard src/*.cpp)
OBJS=$(SRCS:src/%.cpp=obj/%.o)

all: release

release: $(OBJS)
	$(COMPILER) $(LDFLAGS) -o $(EXECUTABLE) $(OBJS) $(LDLIBS) 

obj/%.o: src/%.cpp
	$(COMPILER) $(FLAGS) -o $@ -c $<


zip: dist-clean
ifdef TEAM_ID
	zip $(strip $(TEAM_ID)).zip -r ./
else
	@echo "you need to put your TEAM_ID in the Makefile"
endif

submit: zip
ifdef TEAM_ID
	curl -F "file=@$(strip $(TEAM_ID)).zip" -L http://www.intel-software-academic-program.com/contests/ayc/2012-11/upload/upload.php
else
	@echo "you need to put your TEAM_ID in the Makefile"
endif

clean:
	rm -f obj/*
	rm run
dist-clean: clean
	rm -f $(EXECUTABLE) *~ .depend *.zip
	
#automatically handle include dependencies
depend: .depend

.depend: $(SRCS)
	rm -f ./.depend
	@$(foreach SRC, $(SRCS), $(COMPILER) $(FLAGS) -MT $(SRC:src/%.cpp=obj/%.o) -MM $(SRC) >> .depend;)

include .depend	
