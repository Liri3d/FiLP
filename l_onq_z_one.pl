%Мужчины
man(anatoliy).
man(dimitriy).
man(vlad).
man(kirill).
man(mefodiy).
man(vova).
man(ivan).

%Женщины
woman(vladina).
woman(galya).
woman(sveta).
woman(zoya).
woman(katrin).

%Родство
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

%Предикат вывода мужчин
men :- man(X), write(X), nl, fail.
men.

%Предикат вывода женщин
women :- woman(X), write(X), nl, fail.
women.

%Предикат вывода детей
children(X) :- dite(X, Child), write(Child), nl, fail.
children(_).

%Предикат проверки на материнство
mother(X, Y) :- dite(X, Y), woman(X).

%Предикат поиска матери
mother(X) :- mother(Mother, X), write(Mother).

%Предикат проверки является ли X братом
brother(X, Y) :- dite(Parent, X), dite(Parent, Y), man(X), X \= Y.

%Предикат поиска братьев
brothers(X) :- setof(Brother, brother(Brother, X), Brothers), write(Brothers).

%Предикат проверки родных братьев и сестер
b_s(X, Y) :- dite(Parent, X), dite(Parent, Y), X \= Y.

%Предикат поиска всех братьев и сестер
b_s(X) :- setof(Y, b_s(X, Y), Y), write(Y).