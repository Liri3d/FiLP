% Задание 1

% Предикат находит максимальное из чисел. (УНИФ - X, Y, Z | НЕУНИФ - U)
max(X, Y, Z, U) :-
    X >= Y,
    X >= Z,
    U = X.
max(X, Y, Z, U) :-
    Y >= X,
    Y >= Z,
    U = Y.
max(X, Y, Z, U) :-
    Z >= X,
    Z >= Y,
    U = Z.

% Предикат находит факторил первого аргумента при помощи рекурсии вверх. (УНИФ - N | НЕУНИФ - X, N1, X1)
fact(0, 1).

fact(N, X) :-
    N > 0,
    N1 is N - 1,
    fact(N1, X1),
    X is N * X1.

% Предикат находит факторил первого аргумента при помощи рекурсии вниз. (УНИФ - N | НЕУНИФ - X, N1, X1, Acc, Acc1)
factd(N, X) :-
    N > 0,
    fact_helper(N, 1, X).
fact_helper(0, Acc, Acc).
fact_helper(N, Acc, X) :-
    N > 0,
    N1 is N - 1,
    Acc1 is Acc * N,
    fact_helper(N1, Acc1, X).

% Предикат находит сумму цифр числа с помощью рекурсии вверх. (УНИФ - N | НЕУНИФ - Sum, Sum1, Remainder, Quotien)
sum_of_digits(N, Sum) :-
    N < 10,
    Sum is N.
sum_of_digits(N, Sum) :-
    N >= 10,
    Quotient is N div 10,
    Remainder is N mod 10,
    sum_of_digits(Quotient, Sum1),
    Sum is Sum1 + Remainder.

% Предикат находит сумму цифр числа с помощью рекурсии вниз. (УНИФ - N | НЕУНИФ - Sum, Acc, Acc1, Remainder, Quotien)
sum_of_digits_d(N, Sum) :-
    sum_of_digits_helper(N, 0, Sum).

sum_of_digits_helper(0, Acc, Acc).
sum_of_digits_helper(N, Acc, Sum) :-
    Quotient is N div 10,
    Remainder is N mod 10,
    Acc1 is Acc + Remainder,
    sum_of_digits_helper(Quotient, Acc1, Sum).

% Предикат проверяет, является ли число свободным от квадратов. (УНИФ - N | НЕУНИФ - X)
is_square_free(1).
is_square_free(N) :-
    N > 1,
    \+ has_square_divisor(N, 2).

has_square_divisor(N, X) :-
    X * X =< N,
    (N mod (X * X) =:= 0 ;
    has_square_divisor(N, X+1)).

% Предикат чтения списка с клавиатуры. (УНИФ - user_input | НЕУНИФ - List, Codes, Atom, Atoms)
read_list(List) :-
    read_line_to_codes(user_input, Codes),
    atom_codes(Atom, Codes),
    atomic_list_concat(Atoms, ' ', Atom),
    maplist(atom_number, Atoms, List).

% Предикат вывода списка на экран. (УНИФ - X | НЕУНИФ - Xs)
write_list([]).
write_list([X|Xs]) :-
    write(X),
    write(' '),
    write_list(Xs).

% Предикат проверяет, является ли Sum суммой элементов списка или записывает в эту переменную сумму элементов. (УНИФ - List | НЕУНИФ - X, Xs, Sum, RestSum)
sum_list_d([], 0).
sum_list_d([X|Xs], Sum) :-
    sum_list_d(Xs, RestSum),
    Sum is X + RestSum.

sum_list_down :-
    writeln('Введите список элементов (разделенных пробелами):'),
    read_list(List),
    sum_list_d(List, Sum),
    writeln('Сумма элементов списка:'),
    write(Sum).

% Предикат суммирования элементов списка c исп. предикатов. (УНИФ - List | НЕУНИФ - X, Xs, Sum, Sum1)
sum_list_el([], 0).
sum_list_el([X|Xs], Sum) :-
    sum_list_el(Xs, Sum1),
    Sum is X + Sum1.

sum_list :-
    write('Введите список элементов (через пробел): '),
    read_list(List),
    sum_list_el(List, Sum),
    write('Сумма элементов списка: '),
    write(Sum).

% Предикат проверяет, является ли Sum суммой элементов списка или записывает в эту переменную сумму элементов. Рекурсия вверх. (УНИФ - X | НЕУНИФ - Xs, Sum, Sum1)
sum_list_up([], 0).
sum_list_up([X|Xs], Sum) :-
    sum_list_up(Xs, Sum1),
    Sum is Sum1 + X.


%  Предикат, который удаляет все элементы, сумма цифр которых равна данной. (УНИФ - List, Sum | НЕУНИФ - X, Xs, NewList, SumX, Rest)
remove_by_sum([], _, []).
remove_by_sum([X|Xs], Sum, NewList) :-
    sum_of_digits(X, SumX),
    SumX =\= Sum,
    remove_by_sum(Xs, Sum, Rest),
    NewList = [X|Rest].
remove_by_sum([_|Xs], Sum, NewList) :-
    remove_by_sum(Xs, Sum, NewList).

remove_elements_with_sum :-
    write('Введите список элементов: '),
    read_list(List),
    write('Введите сумму цифр: '),
    read(Sum),
    remove_by_sum(List, Sum, NewList),
    write('Новый список без элементов с суммой цифр равной '),
    write(Sum),
    write(': '),
    write_list(NewList).



% Вариант №4

% Задание 2
  
%Найти произведение цифр числа с помощью рекурсии вверх. (УНИФ - N | НЕУНИФ - Prod, Prod1, Remainder, Quotien)
product_numbers(N, Prod) :-
    N < 10,
    Prod is N.
product_numbers(N, Prod) :-
    N >= 10,
    Quotient is N div 10,
    Remainder is N mod 10,
    product_numbers(Quotient, Prod1),
    Prod is Prod1 * Remainder.

% C помощью рекурсии вниз. (УНИФ - N | НЕУНИФ - Prod, Acc, Acc1, Remainder, Quotien)
product_numbers_d(N, Prod) :-
    product_numbers_helper(N, 1, Prod).

product_numbers_helper(0, Acc, Acc).
product_numbers_helper(N, Acc, Prod) :-
    Quotient is N div 10,
    Remainder is N mod 10,
    Acc1 is Acc * Remainder,
    product_numbers_helper(Quotient, Acc1, Prod).






%Найти максимальную цифры числа, не делящуюся на 3 с помощью рекурсии вверх. (УНИФ - N | НЕУНИФ - MaxDigit, LastDigit, NextN, MaxDigit1)
find_max_digit(N, MaxDigit) :-
    N < 10,
    MaxDigit is N.

find_max_digit(N, MaxDigit) :-
    N >= 10,
    LastDigit is N mod 10,
    NextN is N div 10,
    (NextN is 0 ->
        (LastDigit mod 3 =\= 0 ->
            MaxDigit is LastDigit
        ;
            find_max_digit(NextN, MaxDigit)
        )
    ;
        find_max_digit(NextN, MaxDigit1),
        (LastDigit mod 3 =\= 0, LastDigit > MaxDigit1 ->
            MaxDigit is LastDigit
        ;
            MaxDigit is MaxDigit1
        )
    ).

% С помощью рекурсии вниз. (УНИФ - N | НЕУНИФ - MaxDigit, LastDigit, NextN, MaxDigit1)
find_max_digit_d(N, MaxDigit) :-
    N < 10,
    MaxDigit is N.
find_max_digit_d(N, MaxDigit) :-
    N >= 10,
    NextN is N div 10,
    find_max_digit_d(NextN, MaxDigit1),
    LastDigit is N mod 10,
    (LastDigit mod 3 =\= 0, LastDigit > MaxDigit1 ->
        MaxDigit is LastDigit
    ;
        MaxDigit is MaxDigit1
    ).




%Найти количество делителей числа c помощью рекурсии вверх. (УНИФ - N | НЕУНИФ - Count, Divisor, NextDivisor, Count1)
count_divisors(N, Count) :-
    count_divisors_helper(N, 1, Count).

count_divisors_helper(N, Divisor, Count) :-
    Divisor > N // 2,
    Count is 1. % базовый случай: число делителей равно 1 (число делится только на себя)
count_divisors_helper(N, Divisor, Count) :-
    Divisor =< N // 2,
    (N mod Divisor =:= 0 ->
        NextDivisor is Divisor + 1,
        count_divisors_helper(N, NextDivisor, Count1),
        Count is Count1 + 1
    ;
        NextDivisor is Divisor + 1,
        count_divisors_helper(N, NextDivisor, Count)
    ).

% С помощью рекурсии вниз.(УНИФ - N | НЕУНИФ - Count, Divisor, NextDivisor, Count1)
count_divisors_d(N, Count) :-
    count_divisors_d_helper(N, 1, Count).

count_divisors_d_helper(N, Divisor, Count) :-
    (Divisor > N ->
        Count is 0 % базовый случай: делитель превысил число, делителей нет
    ;
        (N mod Divisor =:= 0 ->
            NextDivisor is Divisor + 1,
            count_divisors_d_helper(N, NextDivisor, Count1),
            Count is Count1 + 1 % число делителей увеличивается на 1
        ;
            NextDivisor is Divisor + 1,
            count_divisors_d_helper(N, NextDivisor, Count)
        )
    ).





% Задание 3

% 4. Дан целочисленный массив. Вывести индексы массива в том порядке, в
%котором соответствующие им элементы образуют убывающую последовательность.

%(УНИФ - Array | НЕУНИФ - Indexes, IndexedArray, Index, IndexedT, IndexH, NewIndex, T, SortedArray, Rest, RestIndex, RestIndexes)
assign_and_get_descending_indexes(Array, Indexes) :-
    assign_indexes(Array, IndexedArray),
    get_descending_indexes(IndexedArray, Indexes).

% Предикат для присвоения индексов массиву
assign_indexes(Array, IndexedArray) :-
    assign_indexes(Array, 1, IndexedArray).
assign_indexes([], _, []).
assign_indexes([H|T], Index, [(Index,H)|IndexedT]) :-
    NewIndex is Index + 1,
    assign_indexes(T, NewIndex, IndexedT).

% Предикат для получения убывающей последовательности 
get_descending_indexes([], []).
get_descending_indexes(Array, Indexes) :-
    % Сортировка по убыванию элементов
    sort(2, @>=, Array, SortedArray),
    % Берем индексы из отсортированного массива
    extract_indexes(SortedArray, Indexes).
extract_indexes([], []).
extract_indexes([(Index, _) | Rest], [Index | RestIndexes]) :-
    extract_indexes(Rest, RestIndexes).



% 21. Дан целочисленный массив. Необходимо найти элементы, расположенные
% после первого максимального.
%(УНИФ - List | НЕУНИФ - Elements, Max, X, Xs, CurrentMax, Y)
find_elements_after_max(List, Elements) :-
    find_max(List, Max),
    append(_, [Max|Elements], List).
 
find_max([X|Xs], Max) :-
    find_max(Xs, X, Max).

find_max([], Max, Max).

find_max([X|Xs], CurrentMax, Max) :-
    X > CurrentMax,
    find_max(Xs, X, Max).

find_max([Y|Xs], CurrentMax, Max) :-
    Y =< CurrentMax,
    find_max(Xs, CurrentMax, Max).



% 32. Дан целочисленный массив. Найти количество его локальных максимумов.
% Правило для проверки, является ли элемент массива локальным максимумом

%(УНИФ - Array | НЕУНИФ - Index, Length, Elem, PrevElem, NextElem, Count, Maxes)
is_local_max(Array, Index) :-
    length(Array, Length),
    Index > 1, Index < Length,
    nth1(Index, Array, Elem),
    nth1(Index-1, Array, PrevElem),
    nth1(Index+1, Array, NextElem),
    Elem > PrevElem, Elem > NextElem.

% Правило для подсчета количества локальных максимумов в массиве
count_local_maxes(Array, Count) :-
    findall(Index, is_local_max(Array, Index), Maxes),
    length(Maxes, Count).





% Задание 4 

% Вариант № 4. В бутылке, стакане, кувшине и банке находятся молоко, лимонад, квас
% и вода. Известно, что вода и молоко не в бутылке, сосуд с лимонадом находится между
% кувшином и сосудом с квасом, в банке - не лимонад и не вода. Стакан находится около
% банки и сосуда с молоком. Как распределены эти жидкости по сосудам.