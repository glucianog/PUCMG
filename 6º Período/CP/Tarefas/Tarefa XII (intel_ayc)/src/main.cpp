/*!
 * \file main.cpp
 * \brief This file contains source code that solves the Work Hard - Play Hard problem for the Acceler8 contest
 */
#include <iostream>
#include <cstdlib>
#include <string>
#include <list>
#include <vector>
#include <fstream>
#include <time.h>
#include <omp.h>

using namespace std;


/**
 * Tempo sequencial: 4.41s - 
 *  __- Máquina utilizada possui 4 núcleos -__ 
 * 
 * ****** MÉTODOS PARALELIZADOS = compute_path, compute_cost e apply_discount ******
 * 
 * Tempo paralelo com 1 seção paralelizada extrema (chunk mínimo): 7.52s 
 * Tempo paralelo com 1 seção paralelizada extrema (chunk máximo): 2.46s  
 * Tempo paralelo com 1 seção parelelizada diretiva estática: 2.34s 
 * Tempo paralelo com 1 seção paralelizada diretiva dinamica: 2.33s
 * Tempo paralelo com 1 seção paralelizada com diretiva privada e extrema (chunk mínimo): 4.64s
 * Tempo paralelo com 1 seção paralelizada com diretiva privada e extrema (chunk máximo): 1.52s
 * Tempo paralelo com 1 seção paralelizada com diretiva privada e estática: 1.43s
 * Tempo paralelo com 1 seção paralelizada com diretiva privada e dinâmica: 1.43s 
 * Tempo paralelo com 3 seções paralelizadas com diretiva privada e extrema (chunk mínimo): 4.67s
 * Tempo paralelo com 3 seções paralelizadas com diretiva privada e extrema (chunk máximo): 1.51s 
 * Tempo paralelo com 3 seções paralelizadas com diretiva privada e estática: 1.43s
 * Tempo paralelo com 3 seções paralelizadas com diretiva privada e dinâmica: 1.43s 
 * 
 * 
 * Para a execução com extremas mínimas, cada thread possui um chunk mínimo e, com isso, possui maior latência, o que justifica o tempo de execução.
 * Já para a execução com extremas máximas, o tempo de execução se aproxima do ideal, pois explora a maior quantidade de chunk para as threads, fazendo
 * com que cada núcleo esteja em processamento.
 * Por outro lado, ao observar o chunk extremo máximo e o padrão utilizado, não foi notado grande discrepância de tempo entre eles, isso ocorre, pois a
 * carga está relativamente balanceada entre os núcleos.
 * No código, foi utilizado a diretiva private no vetor "current_city", pois cada thread a utiliza mais de uma vez. Com isso uma cópia é cedida para cada 
 * uma delas, facilitando e agilizando o tempo de processamento ao verificar os dados contidos no vetor.
 * 
 */    



/**
 * \struct Parameters
 * \brief Store the program's parameters.
 * This structure don't need to be modified but feel free to change it if you want.
 */
struct Parameters{
  string from;/*!< The city where the travel begins */
  string to;/*!< The city where the conference takes place */
  unsigned long dep_time_min;/*!< The minimum departure time for the conference (epoch). No flight towards the conference's city must be scheduled before this time. */
  unsigned long dep_time_max;/*!< The maximum departure time for the conference (epoch). No flight towards the conference's city must be scheduled after this time.  */
  unsigned long ar_time_min;/*!< The minimum arrival time after the conference (epoch). No flight from the conference's city must be scheduled before this time.  */
  unsigned long ar_time_max;/*!< The maximum arrival time after the conference (epoch). No flight from the conference's city must be scheduled after this time.  */
  unsigned long max_layover_time;/*!< You don't want to wait more than this amount of time at the airport between 2 flights (in seconds) */
  unsigned long vacation_time_min;/*!< Your minimum vacation time (in seconds). You can't be in a plane during this time. */
  unsigned long vacation_time_max;/*!< Your maximum vacation time (in seconds). You can't be in a plane during this time. */
  list<string> airports_of_interest;/*!< The list of cities you are interested in. */
  string flights_file;/*!< The name of the file containing the flights. */
  string alliances_file;/*!< The name of the file containing the company alliances. */
  string work_hard_file;/*!< The file used to output the work hard result. */
  string play_hard_file;/*!< The file used to output the play hard result. */
  int nb_threads;/*!< The maximum number of worker threads */
};

/**
 * \struct Flight
 * \brief Store a single flight data.
 *This structure don't need to be modified but feel free to change it if you want.
 */
struct Flight{
  string id;/*!< Unique id of the flight. */
  string from;/*!< City where you take off. */
  string to;/*!< City where you land. */
  unsigned long take_off_time;/*!< Take off time (epoch). */
  unsigned long land_time;/*!< Land time (epoch). */
  string company;/*!< The company's name. */
  float cost;/*!< The cost of the flight. */
  float discout;/*!< The discount applied to the cost. */
};

/**
 * \struct Travel
 * \brief Store a travel.
 * This structure don't need to be modified but feel free to change it if you want.
 */
struct Travel{
  vector<Flight> flights;/*!< A travel is just a list of Flight(s). */
};



time_t convert_to_timestamp(int day, int month, int year, int hour, int minute, int seconde);
time_t convert_string_to_timestamp(string s);
void print_params(Parameters &parameters);
void print_flight(Flight& flight);
void read_parameters(Parameters& parameters, int argc, char **argv);
void split_string(vector<string>& result, string line, char separator);
void parse_flight(vector<Flight>& flights, string& line);
void parse_flights(vector<Flight>& flights, string filename);
void parse_alliance(vector<string> &alliance, string line);
void parse_alliances(vector<vector<string> > &alliances, string filename);
bool company_are_in_a_common_alliance(const string& c1, const string& c2, vector<vector<string> >& alliances);
bool has_just_traveled_with_company(Flight& flight_before, Flight& current_flight);
bool has_just_traveled_with_alliance(Flight& flight_before, Flight& current_flight, vector<vector<string> >& alliances);
void apply_discount(Travel & travel, vector<vector<string> >&alliances);
float compute_cost(Travel & travel, vector<vector<string> >&alliances);
void print_alliances(vector<vector<string> > &alliances);
void print_flights(vector<Flight>& flights);
bool nerver_traveled_to(Travel travel, string city);
void print_travel(Travel& travel, vector<vector<string> >&alliances);
void compute_path(vector<Flight>& flights, string to, vector<Travel>& travels, unsigned long t_min, unsigned long t_max, Parameters parameters);
Travel find_cheapest(vector<Travel>& travels, vector<vector<string> >&alliances);
void fill_travel(vector<Travel>& travels, vector<Flight>& flights, string starting_point, unsigned long t_min, unsigned long t_max);
void merge_path(vector<Travel>& travel1, vector<Travel>& travel2);
Travel work_hard(vector<Flight>& flights, Parameters& parameters, vector<vector<string> >& alliances);
void output_work_hard(vector<Flight>& flights, Parameters& parameters, vector<vector<string> >& alliances);
time_t timegm(struct tm *tm);

/**
 * \fn Travel work_hard(vector<Flight>& flights, Parameters& parameters, vector<vector<string> >& alliances)
 * \brief Solve the "Work Hard" problem.
 * This problem can be considered as the easy one. The goal is to find the cheapest way to join a point B from a point A regarding some parameters.
 * \param flights The list of available flights.
 * \param parameters The parameters.
 * \param alliances The alliances between companies.
 * \return The cheapest trip found.
 */
Travel work_hard(vector<Flight>& flights, Parameters& parameters, vector<vector<string> >& alliances){
  vector<Travel> travels;
  //First, we need to create as much travels as it as the number of flights that take off from the
  //first city
  fill_travel(travels, flights, parameters.from, parameters.dep_time_min, parameters.dep_time_max);
  //double start = omp_get_wtime();
  compute_path(flights, parameters.to, travels, parameters.dep_time_min, parameters.dep_time_max, parameters);
  //double end = omp_get_wtime();
  //printf("\nFirst Compute Path Time = %f", end - start); 
  
  vector<Travel> travels_back;
  //Then we need to travel back
  fill_travel(travels_back, flights, parameters.to, parameters.ar_time_min, parameters.ar_time_max);
  //  start = omp_get_wtime();
  compute_path(flights, parameters.from, travels_back, parameters.ar_time_min, parameters.ar_time_max, parameters);
  //end = omp_get_wtime();
  //printf("\nSecond Compute Path Time = %f", end - start); 
  
  merge_path(travels, travels_back);
  Travel go =  find_cheapest(travels, alliances);
  return go;
}

/**
 * \fn void apply_discount(Travel & travel, vector<vector<string> >&alliances)
 * \brief Apply a discount when possible to the flights of a travel.
 * \param travel A travel (it will be modified to apply the discounts).
 * \param alliances The alliances.
 */
void apply_discount(Travel & travel, vector<vector<string> >&alliances){
  if(travel.flights.size()>0)
    travel.flights[0].discout = 1;
  if(travel.flights.size()>1){
    #pragma omp parallel for schedule(dynamic,100)
    for(unsigned int i=1; i<travel.flights.size(); i++){
      Flight& flight_before = travel.flights[i-1];
      Flight& current_flight = travel.flights[i];
      if(has_just_traveled_with_company(flight_before, current_flight)){
	flight_before.discout = 0.7;
	current_flight.discout = 0.7;
      }else if(has_just_traveled_with_alliance(flight_before, current_flight, alliances)){
	if(flight_before.discout >0.8)
	  flight_before.discout = 0.8;
	current_flight.discout = 0.8;
      }else{
	current_flight.discout = 1;
      }
    }
  }
}

/**
 * \fn float compute_cost(Travel & travel, vector<vector<string> >&alliances)
 * \brief Compute the cost of a travel and uses the discounts when possible.
 * \param travel The travel.
 * \param alliances The alliances.
 */
float compute_cost(Travel & travel, vector<vector<string> >&alliances){
  float result = 0;
  apply_discount(travel, alliances);
  #pragma omp parallel for reduction(+:result) schedule(dynamic,100)
  for(unsigned int i=0; i<travel.flights.size(); i++){
    result += (travel.flights[i].cost * travel.flights[i].discout);
  }
  return result;
}

/**
 * \fn void compute_path(vector<Flight>& flights, string to, vector<Travel>& travels, unsigned long t_min, unsigned long t_max, Parameters parameters)
 * \brief Computes a path from a point A to a point B. The flights must be scheduled between t_min and t_max. It is also important to take the layover in consideration.
 * 	You should try to improve and parallelize this function. A lot of stuff can probably done here. You can do almost what you want but the program output must not be modified.
 * \param flights All the flights that are available.
 * \param to The destination.
 * \param travels The list of possible travels that we are building.
 * \param t_min You must not be in a plane before this value (epoch)
 * \param t_max You must not be in a plane after this value (epoch)
 * \param parameters The program parameters
 */
void compute_path(vector<Flight>& flights, string to, vector<Travel>& travels, unsigned long t_min, unsigned long t_max, Parameters parameters){
  vector<Travel> final_travels;
  while(travels.size() > 0){
    Travel travel = travels.back();
    Flight current_city = travel.flights.back();
    travels.pop_back();
    //First, if a direct flight exist, it must be in the final travels
    if(current_city.to == to){
      final_travels.push_back(travel);
    }else{//otherwise, we need to compute a path
      #pragma omp parallel for private(current_city) schedule(dynamic,100)
      for(unsigned int i=0; i<flights.size(); i++){
        Flight flight = flights[i];
        if(flight.from == current_city.to &&
          flight.take_off_time >= t_min &&
          flight.land_time <= t_max &&
          (flight.take_off_time > current_city.land_time) &&
          flight.take_off_time - current_city.land_time <= parameters.max_layover_time &&
          nerver_traveled_to(travel, flight.to)){
          Travel newTravel = travel;
          newTravel.flights.push_back(flight);
          if(flight.to == to){
            final_travels.push_back(newTravel);
          }else{
            travels.push_back(newTravel);
          }
        }
      }
    }
  }
  travels = final_travels;
}

/**
 * \fn Travel find_cheapest(vector<Travel>& travels, vector<vector<string> >&alliances)
 * \brief Finds the cheapest travel amongst the travels's vector.
 * \param travels A vector of acceptable travels
 * \param alliances The alliances
 * \return The cheapest travel found.
 */
Travel find_cheapest(vector<Travel>& travels, vector<vector<string> >&alliances){
  Travel result;
  if(travels.size()>0){
    result = travels.back();
    travels.pop_back();
  }else
    return result;
  while(travels.size()>0){
    Travel temp = travels.back();
    travels.pop_back();
    if(compute_cost(temp, alliances) < compute_cost(result, alliances)){
      result = temp;
    }
  }
  return result;
}

/**
 * \fn void fill_travel(vector<Travel>& travels, vector<Flight>& flights, string starting_point, unsigned long t_min, unsigned long t_max)
 * \brief Fills the travels's vector with flights that take off from the starting_point.
 * This function might probably be improved.
 * \param travels A vector of travels under construction
 * \param flights All the flights that are available.
 * \param starting_point The starting point.
 * \param travels The list of possible travels that we are building.
 * \param t_min You must not be in a plane before this value (epoch).
 * \param t_max You must not be in a plane after this value (epoch).
 */
void fill_travel(vector<Travel>& travels, vector<Flight>& flights, string starting_point, unsigned long t_min, unsigned long t_max){
  for(unsigned int i=0; i< flights.size(); i++){
    if(flights[i].from == starting_point &&
       flights[i].take_off_time >= t_min &&
       flights[i].land_time <= t_max){
      Travel t;
      t.flights.push_back(flights[i]);
      travels.push_back(t);
    }
  }
}

/**
 * \fn void merge_path(vector<Travel>& travel1, vector<Travel>& travel2)
 * \brief Merge the travel1 with the travel2 and put the result in the travel1.
 * \param travel1 The first part of the trip.
 * \param travel2 The second part of the trip.
 */
void merge_path(vector<Travel>& travel1, vector<Travel>& travel2){
  vector<Travel> result;
  for(unsigned int i=0; i<travel1.size(); i++){
    Travel t1 = travel1[i];
    for(unsigned j=0; j<travel2.size(); j++){
      Travel t2 = travel2[j];
      Flight last_flight_t1 = t1.flights.back();
      Flight first_flight_t2 = t2.flights[0];
      if(last_flight_t1.land_time < first_flight_t2.take_off_time){
	Travel new_travel = t1;
	new_travel.flights.insert(new_travel.flights.end(), t2.flights.begin(), t2.flights.end());
	result.push_back(new_travel);
      }
    }
  }
  travel1 = result;
}

/**
 * \fn time_t convert_to_timestamp(int day, int month, int year, int hour, int minute, int seconde)
 * \brief Convert a date to timestamp
 * Parameter's names are self-sufficient. You shouldn't modify this part of the code unless you know what you are doing.
 * \return a timestamp (epoch) corresponding to the given parameters.
 */
time_t convert_to_timestamp(int day, int month, int year, int hour, int minute, int seconde){
  tm time;
  time.tm_year = year - 1900;
  time.tm_mon = month - 1;
  time.tm_mday = day;
  time.tm_hour = hour;
  time.tm_min = minute;
  time.tm_sec = seconde;
  return timegm(&time);
}

/**
 * \fn time_t timegm(struct tm *tm)
 * \brief Convert a tm structure into a timestamp.
 * \return a timestamp (epoch) corresponding to the given parameter.
 */
time_t timegm(struct tm *tm){
  time_t ret;
  char *tz;

  //  tz = getenv("TZ");
  //  setenv("TZ", "", 1);
  //  tzset();
  ret = mktime(tm);
  // if (tz)
  //    setenv("TZ", tz, 1);
  // else
  //   unsetenv("TZ");
  // tzset();
  return ret;
}

/**
 * \fn time_t convert_string_to_timestamp(string s)
 * \brief Parses the string s and returns a timestamp (epoch)
 * \param s A string that represents a date with the following format MMDDYYYYhhmmss with
 * M = Month number
 * D = Day number
 * Y = Year number
 * h = hour number
 * m = minute number
 * s = second number
 * You shouldn't modify this part of the code unless you know what you are doing.
 * \return a timestamp (epoch) corresponding to the given parameters.
 */
time_t convert_string_to_timestamp(string s){
  if(s.size() != 14){
    cerr<<"The given string is not a valid timestamp"<<endl;
    exit(0);
  }else{
    int day, month, year, hour, minute, seconde;
    day = atoi(s.substr(2,2).c_str());
    month = atoi(s.substr(0,2).c_str());
    year = atoi(s.substr(4,4).c_str());
    hour = atoi(s.substr(8,2).c_str());
    minute = atoi(s.substr(10,2).c_str());
    seconde = atoi(s.substr(12,2).c_str());
    return convert_to_timestamp(day, month, year, hour, minute, seconde);
  }
}

/**
 * \fn void print_params(Parameters &parameters)
 * \brief You can use this function to display the parameters
 */
void print_params(Parameters &parameters){
  cout<<"From : "					<<parameters.from					<<endl;
  cout<<"To : "					<<parameters.to						<<endl;
  cout<<"dep_time_min : "			<<parameters.dep_time_min			<<endl;
  cout<<"dep_time_max : "			<<parameters.dep_time_max			<<endl;
  cout<<"ar_time_min : "			<<parameters.ar_time_min			<<endl;
  cout<<"ar_time_max : "			<<parameters.ar_time_max			<<endl;
  cout<<"max_layover_time : "		<<parameters.max_layover_time		<<endl;
  cout<<"vacation_time_min : "	<<parameters.vacation_time_min		<<endl;
  cout<<"vacation_time_max : "	<<parameters.vacation_time_max		<<endl;
  cout<<"flights_file : "			<<parameters.flights_file			<<endl;
  cout<<"alliances_file : "		<<parameters.alliances_file			<<endl;
  cout<<"work_hard_file : "		<<parameters.work_hard_file			<<endl;
  cout<<"play_hard_file : "		<<parameters.play_hard_file			<<endl;
  list<string>::iterator it = parameters.airports_of_interest.begin();
  for(; it != parameters.airports_of_interest.end(); it++)
    cout<<"airports_of_interest : "	<<*it	<<endl;
  cout<<"flights : "				<<parameters.flights_file			<<endl;
  cout<<"alliances : "			<<parameters.alliances_file			<<endl;
  cout<<"nb_threads : "			<<parameters.nb_threads				<<endl;
}

/**
 * \fn void print_flight(Flight& flight)
 * \brief You can use this function to display a flight
 */
void print_flight(Flight& flight, ofstream& output){
  struct tm * take_off_t, *land_t;
  take_off_t = gmtime(((const time_t*)&(flight.take_off_time)));
  output<<flight.company<<"-";
  output<<""<<flight.id<<"-";
  output<<flight.from<<" ("<<(take_off_t->tm_mon+1)<<"/"<<take_off_t->tm_mday<<" "<<take_off_t->tm_hour<<"h"<<take_off_t->tm_min<<"min"<<")"<<"/";
  land_t = gmtime(((const time_t*)&(flight.land_time)));
  output<<flight.to<<" ("<<(land_t->tm_mon+1)<<"/"<<land_t->tm_mday<<" "<<land_t->tm_hour<<"h"<<land_t->tm_min<<"min"<<")-";
  output<<flight.cost<<"$"<<"-"<<flight.discout*100<<"%"<<endl;

}

/**
 * \fn void read_parameters(Parameters& parameters, int argc, char **argv)
 * \brief This function is used to read the parameters
 * \param parameters Represents the structure that will be filled with the parameters.
 */
void read_parameters(Parameters& parameters, int argc, char **argv){
  for(int i=0; i<argc; i++){
    string current_parameter = argv[i];
    if(current_parameter == "-from"){
      parameters.from = argv[++i];
    }else if(current_parameter == "-arrival_time_min"){
      parameters.ar_time_min = convert_string_to_timestamp(argv[++i]);
    }else if(current_parameter == "-arrival_time_max"){
      parameters.ar_time_max = convert_string_to_timestamp(argv[++i]);
    }else if(current_parameter == "-to"){
      parameters.to = argv[++i];
    }else if(current_parameter == "-departure_time_min"){
      parameters.dep_time_min = convert_string_to_timestamp(argv[++i]);
    }else if(current_parameter == "-departure_time_max"){
      parameters.dep_time_max = convert_string_to_timestamp(argv[++i]);
    }else if(current_parameter == "-max_layover"){
      parameters.max_layover_time = atol(argv[++i]);
    }else if(current_parameter == "-vacation_time_min"){
      parameters.vacation_time_min = atol(argv[++i]);
    }else if(current_parameter == "-vacation_time_max"){
      parameters.vacation_time_max = atol(argv[++i]);
    }else if(current_parameter == "-vacation_airports"){
      while(i+1 < argc && argv[i+1][0] != '-'){
	parameters.airports_of_interest.push_back(argv[++i]);
      }
    }else if(current_parameter == "-flights"){
      parameters.flights_file = argv[++i];
    }else if(current_parameter == "-alliances"){
      parameters.alliances_file = argv[++i];
    }else if(current_parameter == "-work_hard_file"){
      parameters.work_hard_file = argv[++i];
    }else if(current_parameter == "-play_hard_file"){
      parameters.play_hard_file = argv[++i];
    }else if(current_parameter == "-nb_threads"){
      parameters.nb_threads = atoi(argv[++i]);
      omp_set_num_threads(parameters.nb_threads);
    }

  }
}

/**
 * \fn void split_string(vector<string>& result, string line, char separator)
 * \brief This function split a string into a vector of strings regarding the separator.
 * \param result The vector of separated strings
 * \param line The line that must be split.
 * \param separator The separator character.
 */
void split_string(vector<string>& result, string line, char separator){
  while(line.find(separator) != string::npos){
    size_t pos = line.find(separator);
    result.push_back(line.substr(0, pos));
    line = line.substr(pos+1);
  }
  result.push_back(line);
}

/**
 * \fn void parse_flight(vector<Flight>& flights, string& line)
 * \brief This function parses a line containing a flight description.
 * \param flights The vector of flights.
 * \param line The line that must be parsed.
 */
void parse_flight(vector<Flight>& flights, string& line){
  vector<string> splittedLine;
  split_string(splittedLine, line, ';');
  if(splittedLine.size() == 7){
    Flight flight;
    flight.id = splittedLine[0];
    flight.from = splittedLine[1];
    flight.take_off_time = convert_string_to_timestamp(splittedLine[2].c_str());
    flight.to = splittedLine[3];
    flight.land_time = convert_string_to_timestamp(splittedLine[4].c_str());
    flight.cost = atof(splittedLine[5].c_str());
    flight.company = splittedLine[6];
    flights.push_back(flight);
  }
}

/**
 * \fn void parse_flights(vector<Flight>& flights, string filename)
 * \brief This function parses the flights from a file.
 * \param flights The vector of flights.
 * \param filename The name of the file containing the flights.
 */
void parse_flights(vector<Flight>& flights, string filename){
  string line = "";
  ifstream file;
  file.open(filename.c_str());
  if(!file.is_open()){
    cerr<<"Problem while opening the file "<<filename<<endl;
    exit(0);
  }
  while (!file.eof())
    {
      getline(file, line);
      parse_flight(flights, line);
    }
}

/**
 * \fn void parse_alliance(vector<string> &alliance, string line)
 * \brief This function parses a line containing alliances between companies.
 * \param alliance A vector of companies sharing a same alliance.
 * \param line A line that contains the name of companies in the same alliance.
 */
void parse_alliance(vector<string> &alliance, string line){
  vector<string> splittedLine;
  split_string(splittedLine, line, ';');
  for(unsigned int i=0; i<splittedLine.size(); i++){
    alliance.push_back(splittedLine[i]);
  }
}
/**
 * \fn void parse_alliances(vector<vector<string> > &alliances, string filename)
 * \brief This function parses a line containing alliances between companies.
 * \param alliances A 2D vector representing the alliances. Companies on the same line are in the same alliance.
 * \param filename The name of the file containing the alliances description.
 */
void parse_alliances(vector<vector<string> > &alliances, string filename){
  string line = "";
  ifstream file;

  file.open(filename.c_str());
  if(!file.is_open()){
    cerr<<"Problem while opening the file "<<filename<<endl;
    exit(0);
  }
  while (!file.eof())
    {
      vector<string> alliance;
      getline(file, line);
      parse_alliance(alliance, line);
      alliances.push_back(alliance);
    }
}

/**
 * \fn bool company_are_in_a_common_alliance(const string& c1, const string& c2, vector<vector<string> >& alliances)
 * \brief Check if 2 companies are in the same alliance.
 * \param c1 The first company's name.
 * \param c2 The second company's name.
 * \param alliances A 2D vector representing the alliances. Companies on the same line are in the same alliance.
 */
bool company_are_in_a_common_alliance(const string& c1, const string& c2, vector<vector<string> >& alliances){
  bool result = false;
  for(unsigned int i=0; i<alliances.size(); i++){
    bool c1_found = false, c2_found = false;
    for(unsigned int j=0; j<alliances[i].size(); j++){
      if(alliances[i][j] == c1) c1_found = true;
      if(alliances[i][j] == c2) c2_found = true;
    }
    result |= (c1_found && c2_found);
  }
  return result;
}

/**
 * \fn bool has_just_traveled_with_company(vector<Flight>& flights_before, Flight& current_flight)
 * \brief The 2 last flights are with the same company.
 * \param flight_before The first flight.
 * \param current_flight The second flight.
 * \return The 2 flights are with the same company
 */
bool has_just_traveled_with_company(Flight& flight_before, Flight& current_flight){
  return flight_before.company == current_flight.company;
}

/**
 * \fn bool has_just_traveled_with_alliance(Flight& flight_before, Flight& current_flight, vector<vector<string> >& alliances)
 * \brief The 2 last flights are with the same alliance.
 * \param flight_before The first flight.
 * \param current_flight The second flight.
 * \param alliances The alliances.
 * \return The 2 flights are with the same alliance.
 */
bool has_just_traveled_with_alliance(Flight& flight_before, Flight& current_flight, vector<vector<string> >& alliances){
  return company_are_in_a_common_alliance(current_flight.company, flight_before.company, alliances);
}

/**
 * \fn void print_alliances(vector<vector<string> > &alliances)
 * \brief Display the alliances on the standard output.
 * \param alliances The alliances.
 */
void print_alliances(vector<vector<string> > &alliances){
  for(unsigned int i=0; i<alliances.size(); i++){
    cout<<"Alliance "<<i<<" : ";
    for(unsigned int j=0; j<alliances[i].size(); j++){
      cout<<"**"<<alliances[i][j]<<"**; ";
    }
    cout<<endl;
  }
}

/**
 * \fn void print_flights(vector<Flight>& flights)
 * \brief Display the flights on the standard output.
 * \param flights The flights.
 */
void print_flights(vector<Flight>& flights, ofstream& output){
  for(unsigned int i=0; i<flights.size(); i++)
    print_flight(flights[i], output);
}

/**
 * \fn bool nerver_traveled_to(Travel travel, string city)
 * \brief Indicates if the city has already been visited in the travel. This function is used to avoid stupid loops.
 * \param travel The travels.
 * \apram city The city.
 * \return The current travel has never visited the given city.
 */
bool nerver_traveled_to(Travel travel, string city){
  for(unsigned int i=0; i<travel.flights.size(); i++)
    if(travel.flights[i].from == city || travel.flights[i].to == city)
      return false;
  return true;
}

/**
 * \fn void print_travel(Travel& travel, vector<vector<string> >&alliances)
 * \brief Display a travel on the standard output.
 * \param travel The travel.
 * \param alliances The alliances (used to compute the price).
 */
void print_travel(Travel& travel, vector<vector<string> >&alliances, ofstream& output){
  output<<"Price : "<<compute_cost(travel, alliances)<<endl;
  print_flights(travel.flights, output);
  output<<endl;
}

/**
 * \fn void output_work_hard(vector<Flight>& flights, Parameters& parameters, vector<vector<string> >& alliances)
 * \brief Display the solution of the "Work Hard" problem by solving it first.
 * \param flights The list of available flights.
 * \param parameters The parameters.
 * \param alliances The alliances between companies.
 */
void output_work_hard(vector<Flight>& flights, Parameters& parameters, vector<vector<string> >& alliances){
  ofstream output;
  output.open(parameters.work_hard_file.c_str());
  Travel travel = work_hard(flights, parameters, alliances);
  output<<"“Work Hard” Proposition :"<<endl;
  print_travel(travel, alliances, output);
  output.close();
}

int main(int argc, char **argv) {
  Parameters parameters;
  vector<vector<string> > alliances;
  read_parameters(parameters, argc, argv);

  vector<Flight> flights;
  parse_flights(flights, parameters.flights_file);

  parse_alliances(alliances, parameters.alliances_file);

  double start = omp_get_wtime(); 
  output_work_hard(flights, parameters, alliances);
  double end = omp_get_wtime();

  printf("\nWork hard time = %f\n", end - start);
}

//./run -from Paris -to Los\ Angeles -departure_time_min 11152012000000 -departure_time_max 11172012000000 -arrival_time_min 11222012000000 -arrival_time_max 11252012000000 -max_layover 100000 -vacation_time_min 432000 -vacation_time_max 604800 -vacation_airports Rio London Chicago -flights flights.txt -alliances alliances.txt
