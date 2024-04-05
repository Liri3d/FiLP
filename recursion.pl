% Задание 1

% Предикат находит максимальное из чисел.
% max(+X, +Y, +Z, ?U).
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

% max(4, 7, 3, U).



% Предикат находит факторил первого аргумента при помощи рекурсии вверх. (+N, -X, -N1, -X1)
% fact(+N, ?X).
fact(0, 1).
fact(N, X) :-
    N > 0,
    N1 is N - 1,
    fact(N1, X1),
    X is N * X1.

% fact(4, X).

% Предикат находит факторил первого аргумента при помощи рекурсии вниз. (+N, -X, -N1, -X1, -Acc, -Acc1)
% factd(+N, -X).
factd(N, X) :-
    N > 0,
    fact_helper(N, 1, X).

% fact_helper(+N, +Acc, -X).
fact_helper(0, Acc, Acc).
fact_helper(N, Acc, X) :-
    N > 0,
    N1 is N - 1,
    Acc1 is Acc * N,
    fact_helper(N1, Acc1, X).

% factd(4, X).



% Предикат находит сумму цифр числа с помощью рекурсии вверх.
% sum_of_digits(+N, ?Sum).
sum_of_digits(N, Sum) :-
    N < 10,
    Sum is N.
sum_of_digits(N, Sum) :-
    N >= 10,
    Quotient is N div 10,
    Remainder is N mod 10,
    sum_of_digits(Quotient, Sum1),
    Sum is Sum1 + Remainder.

% sum_of_digits(12, Sum).

% Предикат находит сумму цифр числа с помощью рекурсии вниз.
% sum_of_digits_d(+, ?Sum).
sum_of_digits_d(N, Sum) :-
    sum_of_digits_helper(N, 0, Sum).

% sum_of_digits_helper(+N, +Acc, -Sum).
sum_of_digits_helper(0, Acc, Acc).
sum_of_digits_helper(N, Acc, Sum) :-
    Quotient is N div 10,
    Remainder is N mod 10,
    Acc1 is Acc + Remainder,
    sum_of_digits_helper(Quotient, Acc1, Sum).

% sum_of_digits_d(12, Sum).



% Предикат проверяет, является ли число свободным от квадратов. 
% is_square_free(+N).
is_square_free(1).
is_square_free(N) :-
    N > 1,
    \+ has_square_divisor(N, 2).

% has_square_divisor(+N, +X).
has_square_divisor(N, X) :-
    X * X =< N,
    (N mod (X * X) =:= 0 ;
    has_square_divisor(N, X+1)).

% is_square_free(2).



% Предикат чтения списка с клавиатуры. 
% read_list(-List).
read_list(List) :-
    read_line_to_codes(user_input, Codes),
    atom_codes(Atom, Codes),
    atomic_list_concat(Atoms, ' ', Atom),
    maplist(atom_number, Atoms, List).

% read_list(List).
%|: 1 2 3



% Предикат вывода списка на экран. 
% write_list(+[]).
write_list([]).
write_list([X|Xs]) :-
    write(X),
    write(' '),
    write_list(Xs).

% write_list([1, 2, 3]).



% Предикат проверяет, является ли Sum суммой элементов списка или записывает в эту переменную сумму элементов.
% sum_list_d(+[X|Xs], ?Sum). 
sum_list_d([], 0).
sum_list_d([X|Xs], Sum) :-
    sum_list_d(Xs, RestSum),
    Sum is X + RestSum.

% sum_list_d([1, 1], Sum).



% Предикат суммирования элементов списка c исп. предикатов. 
sum_list_down :-
    writeln('Введите список элементов (разделенных пробелами):'),
    read_list(List),
    sum_list_d(List, Sum),
    writeln('Сумма элементов списка:'),
    write(Sum).

% sum_list_down.



% Предикат проверяет, является ли Sum суммой элементов списка или записывает в эту переменную сумму элементов. Рекурсия вверх. 
% sum_list_up(+[X|Xs], ?Sum).
sum_list_up([], 0).
sum_list_up([X|Xs], Sum) :-
    sum_list_up(Xs, Sum1),
    Sum is Sum1 + X.

% sum_list_up([1, 2, 3], Sum).



%  Предикат, который удаляет все элементы, сумма цифр которых равна данной. 
% remove_by_sum(+[X|Xs], +Sum, -NewList).
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

% remove_by_sum([1, 111, 1, 21, 1, 3], 3, NewList).
% remove_elements_with_sum.


% Вариант №4

% Задание 2
  
%Найти произведение цифр числа с помощью рекурсии вверх. 
% product_numbers(+N, ?Prod).
product_numbers(N, Prod) :-
    N < 10,
    Prod is N.
product_numbers(N, Prod) :-
    N >= 10,
    Quotient is N div 10,
    Remainder is N mod 10,
    product_numbers(Quotient, Prod1),
    Prod is Prod1 * Remainder.

%  product_numbers(123, Prod).

% C помощью рекурсии вниз. 
% product_numbers_d(+N, ?Prod).
product_numbers_d(N, Prod) :-
    product_numbers_helper(N, 1, Prod).

% product_numbers_helper(+N, +Acc, -Prod)
product_numbers_helper(0, Acc, Acc).
product_numbers_helper(N, Acc, Prod) :-
    Quotient is N div 10,
    Remainder is N mod 10,
    Acc1 is Acc * Remainder,
    product_numbers_helper(Quotient, Acc1, Prod).

% product_numbers_d(123, Prod).



%Найти максимальную цифры числа, не делящуюся на 3 с помощью рекурсии вверх.
% find_max_digit(+N, ?MaxDigit).
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

% find_max_digit(12345, MaxDigit).

% С помощью рекурсии вниз.
% find_max_digit_d(+N, ?MaxDigit).
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

% find_max_digit_d(12345, MaxDigit).



%Найти количество делителей числа c помощью рекурсии вверх.
% count_divisors(+N, ?Count).
count_divisors(N, Count) :-
    count_divisors_helper(N, 1, Count).

% count_divisors_helper(+N, +Divisor, -Count).
count_divisors_helper(N, Divisor, Count) :-
    Divisor > N // 2,
    Count is 1.
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

% count_divisors(2, Count).

% С помощью рекурсии вниз.
% count_divisors_d(+N, ?Count).
count_divisors_d(N, Count) :-
    count_divisors_d_helper(N, 1, Count).

% count_divisors_d_helper(+N, +Divisor, -Count).
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

% count_divisors_d(2, Count).



% Задание 3

% 4. Дан целочисленный массив. Вывести индексы массива в том порядке, в
%котором соответствующие им элементы образуют убывающую последовательность.

% assign_and_get_descending_indexes(+Array, ?Indexes).
assign_and_get_descending_indexes(Array, Indexes) :-
    assign_indexes(Array, IndexedArray),
    get_descending_indexes(IndexedArray, Indexes).

% Предикат для присвоения индексов массиву
% assign_indexes(+Array, -IndexedArray).
assign_indexes(Array, IndexedArray) :-
    assign_indexes(Array, 1, IndexedArray).

% assign_indexes(+[], +_, -[]).
assign_indexes([], _, []).
assign_indexes([H|T], Index, [(Index,H)|IndexedT]) :-
    NewIndex is Index + 1,
    assign_indexes(T, NewIndex, IndexedT).

% Предикат для получения убывающей последовательности 
% get_descending_indexes(+[], -[]).
get_descending_indexes([], []).
get_descending_indexes(Array, Indexes) :-
    % Сортировка по убыванию элементов
    sort(2, @>=, Array, SortedArray),
    % Берем индексы из отсортированного массива
    extract_indexes(SortedArray, Indexes).
% extract_indexes(+[], -[]).
extract_indexes([], []).
extract_indexes([(Index, _) | Rest], [Index | RestIndexes]) :-
    extract_indexes(Rest, RestIndexes).

% assign_and_get_descending_indexes([1, 2, 3, 4, 5], Indexes).



% 21. Дан целочисленный массив. Необходимо найти элементы, расположенные
% после первого максимального.

% find_elements_after_max(+List, ?Elements).
find_elements_after_max(List, Elements) :-
    find_max(List, Max),
    append(_, [Max|Elements], List).

% find_max(+[X|Xs], -Max).
find_max([X|Xs], Max) :-
    find_max(Xs, X, Max).

% find_max(+[], -Max, -Max).
find_max([], Max, Max).
find_max([X|Xs], CurrentMax, Max) :-
    X > CurrentMax,
    find_max(Xs, X, Max).
find_max([Y|Xs], CurrentMax, Max) :-
    Y =< CurrentMax,
    find_max(Xs, CurrentMax, Max).

% find_elements_after_max([1, 2, 3, 2, 1], Elements).



% 32. Дан целочисленный массив. Найти количество его локальных максимумов.
% Правило для проверки, является ли элемент массива локальным максимумом

% local_maximums_count(+Array, ?Count).
local_maximums_count(Array, Count) :-
    local_maximums_count(Array, 0, Count).

% local_maximums_count(+[], ?Count, ?Count).
local_maximums_count([], Count, Count).
local_maximums_count([H|T], Count, Result) :-
    local_maximums_count(T, Count, Result1),
    local_maximum(H, T),
    Result is Result1 + 1.
local_maximums_count([_|T], Count, Result) :-
    local_maximums_count(T, Count, Result).

% local_maximum(+Num, +[H|_]) 
local_maximum(Num, [H|_]) :-
    Num > H.
local_maximum(Num, [H1, H2|T]) :-
    Num > H1,
    Num > H2,
    local_maximum(Num, [H2|T]).

% local_maximums_count([1, 2, 3, 2, 1], Count).



% Задание 4 
% Вариант № 4. В бутылке, стакане, кувшине и банке находятся молоко, лимонад, квас
% и вода. Известно, что вода и молоко не в бутылке, сосуд с лимонадом находится между
% кувшином и сосудом с квасом, в банке - не лимонад и не вода. Стакан находится около
% банки и сосуда с молоком. Как распределены эти жидкости по сосудам.

% in_list(+[El|_],+El).
in_list([El|_],El).
in_list([_|T],El):-in_list(T,El).

% sprava_next(+A,+B,+[C]).
sprava_next(A,B,[C]):-fail.
sprava_next(A,B,[A|[B|Tail]]).
sprava_next(A,B,[_|List]):-sprava_next(A,B,List).

% sleva_next(+A,+B,+[C]).
sleva_next(A,B,[C]):-fail.
sleva_next(A,B,[B|[A|Tail]]).
sleva_next(A,B,[_|List]):-sleva_next(A,B,List).

% next_to(+A,+B,+List).
next_to(A,B,List):-sprava_next(A,B,List).
next_to(A,B,List):-sleva_next(A,B,List).

pr_ein:- Containers=[_,_,_,_],
        
        % Вода и молоко не в бутылке 
        (in_list(Containers,[bottle,kvas]); in_list(Containers,[bottle,limon])),
        
        % сосуд с лимонадом находится между кувшином и сосудом с квасом
        next_to([_,limon],[jug,_],Containers),
        next_to([_,limon],[_,kvas],Containers),
        
        % В банке - не лимонад и не водаf
        (in_list(Containers,[jar,kvas]); in_list(Containers,[jar,milk])),
        
        % Стакан находится около банки и сосуда с молоком
        next_to([glass,_],[jar,_],Containers),
        next_to([glass,_],[_,milk],Containers),

		in_list(Containers,[WHO1,water]),
		in_list(Containers,[WHO2,limon]),
        in_list(Containers,[WHO3,milk]),
		in_list(Containers,[WHO4,kvas]),
		write(Containers).

% pr_ein.

% ЗАДАНИЕ 8
% Вариант 4 Один из пяти братьев разбил окно.
% Андрей сказал: Это или Витя, или Коля
% Витя сказал: Это сделал не я и не Юра.
% Дима сказал: Нет, один из них сказал правду, а другой неправду.
% Юра сказал: Нет, Дима ты не прав.
% Их отец, которому, конечно можно доверять, уверен, что не менее трех братьев
% сказали правду. Кто разбил окно?
       
pr_ein1:- 
        Brat=[_,_,_,_,_],
        
        % Юра врет
        % Андрей сказал: Это или Витя, или Коля
        (((in_list(Brat,[kolya,vinoven]); in_list(Brat,[vitya,vinoven])),
        (in_list(Brat,[andrey,nevinoven]), in_list(Brat,[yura,nevinoven]), in_list(Brat,[dima,nevinoven]))),

        % Витя сказал: Это сделал не я и не Юра
        ((in_list(Brat,[yura,nevinoven]), in_list(Brat,[vitya,nevinoven])),
        (in_list(Brat,[andrey,vinoven]); in_list(Brat,[kolya,vinoven]); in_list(Brat,[dima,vinoven]))),

        % Дима сказал: Нет, один из них сказал правду, а другой неправду.
        (((in_list(Brat,[kolya,vinoven]); in_list(Brat,[vitya,vinoven])),
        (in_list(Brat,[andrey,nevinoven]), in_list(Brat,[yura,nevinoven]), in_list(Brat,[dima,nevinoven])));
        ((in_list(Brat,[yura,vinoven]); in_list(Brat,[vitya,vinoven])),
        (in_list(Brat,[andrey,vinoven]); in_list(Brat,[kolya,vinoven]); in_list(Brat,[dima,vinoven]))))),

        % Дима врет
        % Андрей сказал: Это или Витя, или Коля
        (((in_list(Brat,[kolya,vinoven]); in_list(Brat,[vitya,vinoven])),
        (in_list(Brat,[andrey,nevinoven]), in_list(Brat,[yura,nevinoven]), in_list(Brat,[dima,nevinoven]))),

        % Витя сказал: Это сделал не я и не Юра
        ((in_list(Brat,[yura,nevinoven]), in_list(Brat,[vitya,nevinoven])),
        (in_list(Brat,[andrey,vinoven]); in_list(Brat,[kolya,vinoven]); in_list(Brat,[dima,vinoven])))),

        % Витя врет
        % Андрей сказал: Это или Витя, или Коля
        ((in_list(Brat,[kolya,vinoven]); in_list(Brat,[vitya,vinoven])),
        (in_list(Brat,[andrey,nevinoven]), in_list(Brat,[yura,nevinoven]), in_list(Brat,[dima,nevinoven]))),

        % Андрей врет
        % Витя сказал: Это сделал не я и не Юра
        ((in_list(Brat,[yura,nevinoven]), in_list(Brat,[vitya,nevinoven])),
        (in_list(Brat,[andrey,vinoven]); in_list(Brat,[kolya,vinoven]); in_list(Brat,[dima,vinoven]))),

        write(Brat).

% pr_ein1.