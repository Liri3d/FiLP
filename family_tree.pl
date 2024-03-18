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
dite(mefodiy, egor).
dite(kirill, sveta).
dite(mefodiy, sveta).
dite(zoya, vlad).
dite(zoya, vova).
dite(zoya, vladina).
dite(sveta, marina).
dite(katrin, vlad).
dite(katrin, vladina).
dite(katrin, sveta).
dite(katrin, zoya).
        
%Предикат проверяет, является ли X дедушкой Y. (УНИФ - X, Y | НЕУНИФ - Z)
grand_pa(X, Y) :- dite(Z, Y), dite(X, Z), man(X).

%Предикат выводит всех дедушек X. (УНИФ - X | НЕУНИФ - Y, Z)
grand_pas(X) :- dite(Z, Y), dite(Y, X), man(Z), write(Z), nl, fail.
grand_pas(_).

%Предикат проверяет, являются ли X и Y 
%дедушкой и внучкой или внучкой и дедушкой. (УНИФ - X, Y)
grand_pa_and_da(X,Y) :- grand_pa(X,Y), woman(Y).
grand_pa_and_da(X,Y) :- grand_pa(Y,X), woman(X).

%Предикат проверяет, является ли X тетей Y.  (УНИФ - X, Y | НЕУНИФ - Z)
aunt(X, Y) :- b_s(X, Z), dite(Z, Y), woman(X).

%Предикат, который выводит всех тетей X.  (УНИФ - X | НЕУНИФ - Y, Z)
aunts(X) :- b_s(Y, Z), dite(Y, X), woman(Z), write(Z), nl, fail.
aunts(_).

%Предикат проверяет, является ли X сыном Y.  (УНИФ - X, Y)
son(X, Y) :- dite(Y, X), man(X). 
son.

%Предикат выводит сына X. (УНИФ - X | НЕУНИФ - Y)
son(X) :- dite(X, Y), man(Y), write(Y), nl, fail.
son(_).

%Предикат проверяет, является ли X мужем Y. (УНИФ - X, Y | НЕУНИФ - Z)
husband(X, Y) :- dite(X, Z), dite(Y, Z), man(X), woman(Y).

%Предикат выводит мужа X. (УНИФ - X | НЕУНИФ - Y, Z)
husband(X) :- dite(X, Y), dite(Z, Y), woman(X), man(Z), write(Z). 

%Предикат вывода мужчин. (НЕУНИФ - X)
man :- man(X), write(X), nl, fail.
man.

%Предикат вывода женщин. (НЕУНИФ - X)
woman :- woman(X), write(X), nl, fail.
woman.

%Предикат вывода детей. (УНИФ - X | НЕУНИФ - Child)
children(X) :- dite(X, Child), write(Child), nl, fail.
children(_).

%Предикат проверки на материнство. (УНИФ - X, Y)
mother(X, Y) :- dite(X, Y), woman(X).

%Предикат поиска матери (УНИФ - X | НЕУНИФ - Mother)
mother(X) :- mother(Mother, X), write(Mother).

%Предикат проверки является ли X братом. (УНИФ - X, Y | НЕУНИФ - Parent)
brother(X, Y) :- dite(Parent, X), dite(Parent, Y), man(X), X \= Y.

%Предикат поиска братьев. (УНИФ - X | НЕУНИФ - Brother)
brothers(X) :- setof(Brother, brother(Brother, X), Brothers), write(Brothers).

%Предикат проверки родных братьев и сестер. (УНИФ - X, Y | НЕУНИФ - Parent)
b_s(X, Y) :- dite(Parent, X), dite(Parent, Y), X \= Y.

%Предикат поиска всех братьев и сестер.  (УНИФ - X | НЕУНИФ - Y)
b_s(X) :- setof(Y, b_s(X, Y), Y), write(Y).