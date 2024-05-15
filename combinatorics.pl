member([El|_],El).
member([_|T],El):-in_list_a(T,El).



length([], 0).
length([_|T], Length) :-
    length(T, RestLength),
    Length is RestLength + 1.

append([], Ys, Ys).
append([X|Xs], Ys, [X|Zs]) :- append(Xs, Ys, Zs).

% Задание 1

% 1.1 Размещения с рекурсией с повторениями из n по k для заданного алфавита.
arrangement_with_repetitions(_, 0, []).
arrangement_with_repetitions(Alphabet, Length, [X|T]) :-
    Length > 0,
    NewLength is Length - 1,
    member(Alphabet, X),
    arrangement_with_repetitions(Alphabet, NewLength, T).

% Пример ввода
% arrangement_with_repetitions([a, b, c], 3, Res).

% 1.2 Cочетания с рекурсией без повторений из n по k для заданного алфавита.
combination(_, 0, []).
combination([X|T], K, [X|Comb]) :-
    K > 0,
    K1 is K - 1,
    combination(T, K1, Comb).
combination([_|T], K, Comb) :-
    K > 0,
    combination(T, K, Comb).
% Пример ввода
% combination([a, b, c, d], 2, Comb).

% 1.3 Размещения без рекурсии с повторениями из n по k для заданного алфавита.
arrangement_repeat(Alphabet, K, Arrangement) :-
    length(Arrangement, K),
    generate_list(Arrangement, Alphabet).

generate_list([], _).
generate_list([X|Rest], Alphabet) :-
    length(Alphabet, N),
    between(1, N, Index),
    nth1(Index, Alphabet, X),
    generate_list(Rest, Alphabet).
% Пример ввода
% arrangement_repeat([a,b,c],2,Arrangement).

% 1.4 Сочетания без рекурсии без повторений из n по k для заданного алфавита.
combination_no_repeat(Alphabet, K, Combination) :-
    length(Alphabet, N),
    numlist(1, N, Indices),
    combination_indices(Indices, K, IndicesComb),
    maplist(nth1_in_list(Alphabet), IndicesComb, Combination).
combination_indices(Indices, K, CombinationIndices) :-
    length(CombinationIndices, K),
    generate_indices(Indices, CombinationIndices).
generate_indices(_, []).
generate_indices(Indices, [Index|Rest]) :-
    select(Index, Indices, RemainingIndices),
    generate_indices(RemainingIndices, Rest).
nth1_in_list(List, Index, Element) :-
    nth1(Index, List, Element).
% Пример ввода
% combination_no_repeat([a, b, c, d], 2, Combination).

% 1.5 Написать функцию, которая строит все слова длины k, содержащие 3 буквы a на
% заданном алфавите.
in_list_a([El|_],El).
in_list_a([_|T],El):-in_list_a(T,El).

razm_povt(_,0,Razm,Razm):-!.
razm_povt(Alphabet,NCur,RazmCur,Razm):-in_list_a(Alphabet, El),NNew is NCur-1,razm_povt(Alphabet,NNew,[El|RazmCur],Razm).

sochet([],_,0):-!.
sochet([H|Sub_set],[H|Set],K):-K1 is K-1, sochet(Sub_set,Set,K1).
sochet(Sub_set,[H|Set],K):-sochet(Sub_set,Set,K).

make_pos_list(K, K, []):-!.
make_pos_list(K, CurPos, [NewPos|TailPos]) :- NewPos is CurPos + 1, make_pos_list(K, NewPos, TailPos).

make_3a_empty_word(K, K, _, []):-!.
make_3a_empty_word(K, CurIndex, [NewIndex|PosTail], [a|Tail]) :- 
	NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosTail, Tail),!.
make_3a_empty_word(K, CurIndex, PosList, [_|Tail]) :- 
	NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosList, Tail).	

build_word([],[],_):-!.
build_word([a|WordTail],[X|WordEmpty3aTail],RestWord) :- 
	nonvar(X),build_word(WordTail,WordEmpty3aTail,RestWord),!.
build_word([Y|WordTail],[X|WordEmpty3aTail],[Y|RestWordTail]) :- 
	var(X),build_word(WordTail,WordEmpty3aTail,RestWordTail).

build_3a_words_of_k(Alphabet,K,Word) :- make_pos_list(K, 0, PosList), 
	sochet(Pos_a_List, PosList, 3), make_3a_empty_word(K, 0, Pos_a_List, WordEmpty3a), Alphabet = [a|NewAlphabet], 
	M is K - 3, razm_povt(NewAlphabet, M, [], RestWord), build_word(Word, WordEmpty3a, RestWord).

% Пример ввода
% build_3a_words_of_k([a, b, c],4,Word).

% 1.6 Написать предикат в Prolog, который получает в неунифицированную переменную
% очередное размещение из n по k с повторениями. Написать предикат, который выводит на
% экран все размещения из n по k с повторениями.
placement(0, _, []).
placement(N, K, [X|T]) :-
    N > 0,
    between(1, K, X),
    N1 is N - 1,
    placement(N1, K, T).

all_placement(N, K) :-
    placement(N, K, Placement),
    write(Placement), nl,
    fail.
all_placement(_, _).
% Пример ввода
% all_placement(2, 3).

% 1.7 Написать предикат в Prolog, который получает в неунифицированную переменную
% очередное сочетание из n по k без повторений. Написать предикат, который выводит на
% экран все размещения из n по k без повторений.
combination_u(0, _, []).
combination_u(N, [X|T], [X|Comb]) :-
  N > 0,
  N1 is N - 1,
  combination_u(N1, T, Comb).
combination_u(N, [_|T], Comb) :-
  N > 0,
  combination_u(N, T, Comb).
combination_all(List, K) :-
    combination_u(K, List, Combination),
    write(Combination), nl,
    fail.
  combination_all(_, _).
% Пример ввода
% combination_all([a, b, c], 2).

% 1.8 Написать предикат в Prolog, который строит все слова длины k, содержащие 3 буквы
% a на заданном алфавите.
build_3a_words_of_k(Alphabet,K,Word) :- 
    make_pos_list(K, 0, PosList), 
	sochet(Pos_a_List, PosList, 3), 
    make_3a_empty_word(K, 0, Pos_a_List, WordEmpty3a), 
    Alphabet = [a|NewAlphabet], 
	M is K - 3, 
    razm_povt(NewAlphabet, M, [], RestWord), 
    build_word(Word, WordEmpty3a, RestWord).
% Пример ввода
% build_3a_words_of_k([a, b, c],4,Word).





% Задание 2

% 1 Дан файл. Прочитать из файла строки и вывести длину наибольшей строки.
find_longest_string_length(Filename, MaxLength) :-
    open(Filename, read, Stream),
    read_lines_pred(Stream, Lines),
    close(Stream),
    find_max_length(Lines, MaxLength).

read_lines_pred(Stream, []) :-
    at_end_of_stream(Stream).
read_lines_pred(Stream, [Line|Lines]) :-
    \+ at_end_of_stream(Stream),
    read_line_to_string(Stream, Line),
    read_lines_pred(Stream, Lines).

read_line_to_string(Stream, Line) :-
    read_line_to_codes(Stream, Codes),
    ( Codes \= end_of_file ->
        string_codes(Line, Codes)
    ; Line = ""
    ).

read_lines_pred(Stream, Lines) :-
    read_line_to_string(Stream, Line),
    read_lines_pred(Stream, Line, Lines).

read_lines_pred(_, "", []) :- !.
read_lines_pred(Stream, Line, [Line|Lines]) :-
    read_line_to_string(Stream, NextLine),
    read_lines_pred(Stream, NextLine, Lines).

find_max_length([], 0).
find_max_length([Line|Lines], MaxLength) :-
    string_length(Line, Length),
    find_max_length(Lines, MaxLengthRest),
    MaxLength is max(Length, MaxLengthRest).
% Пример ввода
% find_longest_string_length('dataset.txt', MaxLength).

% 2 Дан файл. Определить, сколько в файле строк, не содержащих пробелы.
find_no_space_string_count(Filename, Count) :-
    open(Filename, read, Stream),
    read_lines_pred(Stream, Lines),
    close(Stream),
    count_no_space_strings(Lines, Count).

count_no_space_strings([], 0).
count_no_space_strings([Line|Lines], Count) :-
    atom_chars(Line, Chars),
    \+ memberchk(' ', Chars),  % Проверяем, отсутствует ли пробел в строке
    count_no_space_strings(Lines, CountRest),
    Count is CountRest + 1.
count_no_space_strings([_|Lines], Count) :-
    count_no_space_strings(Lines, Count).
% Пример ввода
% find_no_space_string_count('dataset.txt', Count).

% 3 Дан файл, найти и вывести на экран только те строки, в которых букв А
% больше, чем в среднем на строку.
find_lines_with_more_A(Filename) :-
    open(Filename, read, Stream),
    read_lines_pred(Stream, Lines),
    close(Stream),
    calculate_average_A(Lines, Average),
    print_lines_with_more_A(Lines, Average).

calculate_average_A(Lines, Average) :-
    count_A(Lines, TotalCount, TotalLines),
    Average is TotalCount / TotalLines.

count_A([], 0, 0).
count_A([Line|Lines], TotalCount, TotalLines) :-
    count_A(Lines, RestCount, RestLines),
    atom_chars(Line, Chars),
    count_A_in_line(Chars, Count),
    TotalCount is RestCount + Count,
    TotalLines is RestLines + 1.

count_A_in_line([], 0).
count_A_in_line([Char|Chars], Count) :-
    (Char = 'A' ; Char = 'a'), % Учитываем и заглавные, и строчные 'A'
    count_A_in_line(Chars, RestCount),
    Count is RestCount + 1.
count_A_in_line([_|Chars], Count) :-
    count_A_in_line(Chars, Count).

print_lines_with_more_A([], _).
print_lines_with_more_A([Line|Lines], Average) :-
    atom_chars(Line, Chars),
    count_A_in_line(Chars, Count),
    (Count > Average ->
        writeln(Line)
    ;
        true
    ),
    print_lines_with_more_A(Lines, Average).

% find_lines_with_more_A('dataset.txt').



% 4 Дан файл, вывести самое частое слово.

find_most_frequent_word(Filename, MostFrequentWord) :-
    open(Filename, read, Stream),
    read_file_words(Stream, Words),
    close(Stream),
    count_word_frequency(Words, FrequencyMap),
    find_max_frequency_word(FrequencyMap, MostFrequentWord).

read_file_words(Stream, Words) :-
    read_stream_to_codes(Stream, Codes),
    atom_codes(Content, Codes),
    split_string(Content, " \t\n", " \t\n", Words).

read_stream_to_codes(Stream, Codes) :-
    get_code(Stream, Code),
    read_stream_to_codes(Code, Stream, Codes).

read_stream_to_codes(-1, _, []) :- !.
read_stream_to_codes(Code, Stream, [Code|Codes]) :-
    get_code(Stream, NextCode),
    read_stream_to_codes(NextCode, Stream, Codes).

count_word_frequency(Words, FrequencyMap) :-
    count_word_frequency(Words, [], FrequencyMap).

count_word_frequency([], FrequencyMap, FrequencyMap).
count_word_frequency([Word|Words], TempMap, FrequencyMap) :-
    (   select(Word-Count, TempMap, RestMap)
    ->  NewCount is Count + 1,
        count_word_frequency(Words, [Word-NewCount|RestMap], FrequencyMap)
    ;   count_word_frequency(Words, [Word-1|TempMap], FrequencyMap)
    ).

find_max_frequency_word(FrequencyMap, MostFrequentWord) :-
    find_max_frequency_word(FrequencyMap, "", 0, MostFrequentWord).

find_max_frequency_word([], Word, _, Word).
find_max_frequency_word([Word-Count|Words], CurrWord, CurrCount, MaxWord) :-
    (   Count > CurrCount
    ->  find_max_frequency_word(Words, Word, Count, MaxWord)
    ;   find_max_frequency_word(Words, CurrWord, CurrCount, MaxWord)
    ).

% find_most_frequent_word('dataset.txt', MostFrequentWord).



% 5 Дан файл, вывести в отдельный файл строки, состоящие из слов, не
% повторяющихся в исходном файле.

go_search_sentence(InputFile, OutputFile) :-
    find_list_word(InputFile, MostFrequentWord),
    sentencesWithMostFrequentWords(InputFile, OutputFile, MostFrequentWord).

find_list_word(Filename, MostFrequentWord) :-
    open(Filename, read, Stream),
    read_file_words(Stream, Words),
    close(Stream),
    count_word_frequency(Words, FrequencyMap),
    find_least_frequent_words(FrequencyMap, MostFrequentWord).

find_least_frequent_words(FrequencyMap, LeastFrequentWords) :-
    find_least_frequent_words(FrequencyMap, [], 9999999, LeastFrequentWords).

find_least_frequent_words([], Words, _, Words).
find_least_frequent_words([Word-Count|Words], LeastFrequentWords, CurrCount, Result) :-
    (   Count < CurrCount
    ->  find_least_frequent_words(Words, [Word], Count, Result)
    ;   Count =:= CurrCount
    ->  find_least_frequent_words(Words, [Word|LeastFrequentWords], CurrCount, Result)
    ;   find_least_frequent_words(Words, LeastFrequentWords, CurrCount, Result)
    ).

sentencesWithMostFrequentWords(InputFile, OutputFile, MostFrequentWords) :-
    find_list_word(InputFile, Words),
    selectSentencesWithWords(InputFile, OutputFile, MostFrequentWords, Sentences),
    writeFile(OutputFile, Sentences).

selectSentencesWithWords(InputFile, OutputFile, MostFrequentWords, Sentences) :-
    open(InputFile, read, Stream),
    open(OutputFile, write, StreamOut),
    process_sentences(Stream, StreamOut, MostFrequentWords, Sentences),
    close(Stream),
    close(StreamOut).

process_sentences(Stream, StreamOut, MostFrequentWords, Sentences) :-
    repeat,
    read_line_to_string(Stream, Sentence),
    (Sentence = end_of_file ->
        true;
        (
            split_string(Sentence, " ", "", Words),
            hasCommonWords(Words, MostFrequentWords),
            writeln(StreamOut, Sentence),
            process_sentences(Stream, StreamOut, MostFrequentWords, Sentences)
        )
    ).

hasCommonWords([], _) :- fail.
hasCommonWords([Word|_], Words) :-
    member(Word, Words),
    !.
hasCommonWords([_|T], Words) :-
    hasCommonWords(T, Words).

writeFile(_, []).

writeFile(File, [Sentence|Sentences]) :-
    writeln(File, Sentence),
    writeFile(File, Sentences).

% go_search_sentence('dataset_min.txt', 'output_sentences.txt').








% Задание 4

% Комбинаторные объекты: 

% Перестановки
permutation(List, OutputFile) :-
    open(OutputFile, write, Stream),
    findall(Perm, perm(List, Perm), Perms),
    write(Stream, Perms), nl(Stream),
    close(Stream).

perm([], []).
perm(List, [H|Perm]) :-
    select(H, List, Rest),
    perm(Rest, Perm).
% Пример ввода
% permutation([1, 2, 3], 'permutations.txt').

% Подмножества
subsets(List, OutputFile) :-
    open(OutputFile, write, Stream),
    findall(Subset, subset(List, Subset), Subsets),
    write(Stream, Subsets), nl(Stream),
    close(Stream).

subset([], []).
subset([H|T], [H|Sub]) :-
    subset(T, Sub).
subset([_|T], Sub) :-
    subset(T, Sub).
% Пример ввода
% subsets([1, 2, 3], 'subsets.txt').























































% Слова длины 6, содержащие 3 буквы b.

build_words([], []).
build_words([H|T], Word) :-
  build_words(T, Rest),
  member(H, Word),
  append([H], Rest, Word).











% Слова длины 6, в которых ровно 2 буквы повторяются 2 раза,
% остальные буквы не повторяются.








