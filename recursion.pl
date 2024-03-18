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


% Предикат, который удаляет все элементы, сумма цифр которых равна данной. (УНИФ - List, Sum | НЕУНИФ - X, Xs, NewList, SumX, Rest)
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