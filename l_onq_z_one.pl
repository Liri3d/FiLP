man(anatoliy).
man(dimitriy).
man(vlad).
man(kirill).
man(mefodiy).
man(vova).
man(ivan).

woman(vladina).
woman(galya).
woman(sveta).
woman(zoya).
woman(katrin).

dite(dimitriy, anatoliy).
dite(dimitriy, galya).
dite(dimitriy, vova).
dite(dimitriy, ivan).
dite(vladina, anatoliy).
dite(vladina, galya).
dite(kirill, dimitriy).
dite(mefodiy, dimitriy).
dite(kirill, sveta).
dite(mefodiy, sveta).
dite(zoya, vlad).
dite(zoya, vladina).
dite(katrin, vlad).
dite(katrin, vladina).

men :- man(X), write(X), nl, fail.
men.

women :- woman(X), write(X), nl, fail.
women.

children(X) :- dite(X, Child), write(Child), nl, fail.
children(_).

mother(X, Y) :- dite(X, Y), woman(X).

mother(X) :- mother(Mother, X), write(Mother).

brother(X, Y) :- dite(Parent, X), dite(Parent, Y), man(X), X \= Y.

brothers(X) :- setof(Brother, brother(Brother, X), Brothers), write(Brothers).

b_s(X, Y) :- dite(Parent, X), dite(Parent, Y), X \= Y.

b_s(X) :- setof(Sib, b_s(X, Y), Y), write(Y).