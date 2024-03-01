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



fact(0, 1).

fact(N, X) :-
    N > 0,
    N1 is N - 1,
    fact(N1, X1),
    X is N * X1.

factd(N, X) :-
    N > 0,
    fact_helper(N, 1, X).
fact_helper(0, Acc, Acc).
fact_helper(N, Acc, X) :-
    N > 0,
    N1 is N - 1,
    Acc1 is Acc * N,
    fact_helper(N1, Acc1, X).



sum_of_digits(N, Sum) :-
    N < 10,
    Sum is N.
sum_of_digits(N, Sum) :-
    N >= 10,
    Quotient is N div 10,
    Remainder is N mod 10,
    sum_of_digits(Quotient, Sum1),
    Sum is Sum1 + Remainder.

sum_of_digits_d(N, Sum) :-
    sum_of_digits_helper(N, 0, Sum).

sum_of_digits_helper(0, Acc, Acc).
sum_of_digits_helper(N, Acc, Sum) :-
    Quotient is N div 10,
    Remainder is N mod 10,
    Acc1 is Acc + Remainder,
    sum_of_digits_helper(Quotient, Acc1, Sum).



is_square_free(1).
is_square_free(N) :-
    N > 1,
    \+ has_square_divisor(N, 2).

has_square_divisor(N, X) :-
    X * X =< N,
    (N mod (X * X) =:= 0 ;
    has_square_divisor(N, X+1)).






read_list(List) :-
    read_line_to_codes(user_input, Codes),
    atom_codes(Atom, Codes),
    atomic_list_concat(Atoms, ' ', Atom),
    maplist(atom_number, Atoms, List).

write_list([]).
write_list([X|Xs]) :-
    write(X),
    write(' '),
    write_list(Xs).



sum_list_down([], 0).
sum_list_down([X|Xs], Sum) :-
    sum_list_down(Xs, RestSum),
    Sum is X + RestSum.


main :-
    writeln('Введите список элементов (разделенных пробелами):'),
    read_list(List),
    sum_list_down(List, Sum),
    writeln('Сумма элементов списка:'),
    write(Sum).




sum_list_up([], 0).
sum_list_up([X|Xs], Sum) :-
    sum_list_up(Xs, RestSum),
    Sum is X + RestSum.



remove_by_sum([], _, []).
remove_by_sum([X|Xs], Sum, NewList) :-
    sum_of_digits(X, SumX),
    SumX =\= Sum,
    remove_by_sum(Xs, Sum, Rest),
    NewList = [X|Rest].
remove_by_sum([_|Xs], Sum, NewList) :-
    remove_by_sum(Xs, Sum, NewList).
