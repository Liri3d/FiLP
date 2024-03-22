member([El|_],El).
member([_|T],El):-in_list(T,El).

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
    member(Alphabet, X),
    NewLength is Length - 1,
    arrangement_with_repetitions(Alphabet, NewLength, T).
% Пример ввода
% arrangement_with_repetitions([a, b, c], 3, Result).

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
sochet([],_,0):-!.
sochet([H|Sub_set],[H|Set],K):-
    K1 is K-1, 
    sochet(Sub_set,Set,K1).
sochet(Sub_set,[H|Set],K):-
    sochet(Sub_set,Set,K).

make_pos_list(K, K, []):-!.
make_pos_list(K, CurPos, [NewPos|TailPos]) :- 
    NewPos is CurPos + 1, 
    make_pos_list(K, NewPos, TailPos).

make_3a_empty_word(K, K, _, []):-!.
make_3a_empty_word(K, CurIndex, [NewIndex|PosTail], [a|Tail]) :- 
	NewIndex is CurIndex + 1, 
    make_3a_empty_word(K, NewIndex, PosTail, Tail),!.
make_3a_empty_word(K, CurIndex, PosList, [_|Tail]) :- 
	NewIndex is CurIndex + 1, 
    make_3a_empty_word(K, NewIndex, PosList, Tail).	

build_word([],[],_):-!.
build_word([a|WordTail],[X|WordEmpty3aTail],RestWord) :- 
	nonvar(X),
    build_word(WordTail,WordEmpty3aTail,RestWord),!.
build_word([Y|WordTail],[X|WordEmpty3aTail],[Y|RestWordTail]) :- 
	var(X),
    build_word(WordTail,WordEmpty3aTail,RestWordTail).

funk_build_3a_words_of_k(Alphabet,K,Word) :- 
    make_pos_list(K, 0, PosList), 
	sochet(Pos_a_List, PosList, 3), 
    make_3a_empty_word(K, 0, Pos_a_List, WordEmpty3a), 
    Alphabet = [a|NewAlphabet], 
	M is K - 3, 
    razm_povt(NewAlphabet, M, [], RestWord), 
    build_word(Word, WordEmpty3a, RestWord).
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
% funk_build_3a_words_of_k([a, b, c],4,Word).