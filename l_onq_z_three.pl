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
dite(zoya, vova).
dite(zoya, vladina).
dite(sveta, marina).
dite(katrin, vlad).
dite(katrin, vladina).
dite(katrin, sveta).
dite(katrin, zoya).

%Предикат проверяет, является ли X дедушкой Y.
grand_pa(X, Y) :- dite(Z, Y), dite(X, Z), man(X).

%Предикат выводит всех дедушек X.
grand_pas(X) :- dite(Z, Y), dite(Y, X), man(Z), write(Z), nl, fail.
grand_pas(_).

%Предикат проверяет, являются ли X и Y 
%дедушкой и внучкой или внучкой и дедушкой.
grand_pa_and_da(X,Y) :- grand_pa(X,Y).
grand_pa_and_da(X,Y) :- grand_pa(Y,X).

%Предикат проверяет, является ли X тетей Y. 
aunt(X, Y) :- b_s(X, Z), dite(Z, Y), woman(X).

%Предикат, который выводит всех тетей X.
aunts(X) :- b_s(Y, Z), dite(Y, X), woman(Z), write(Z), nl, fail.
aunts(_).

%Предикат проверяет, является ли X сыном Y.
son(X, Y) :- dite(Y, X), man(X), man(Y).
son.

%Предикат выводит сына X.
son(X) :- dite(X, Y), man(Y), write(Y), nl, fail.
son(_).

%Предикат проверяет, является ли X мужем Y.
husband(X, Y) :- dite(X, Z), dite(Y, Z), man(X), woman(Y).

%Предикат выводит мужа X.
husband(X) :- dite(X, Y), dite(Z, Y), woman(X), man(Z), write(Z). 

%Предикат вывода мужчин
man :- man(X), write(X), nl, fail.
man.

%Предикат вывода женщин
woman :- woman(X), write(X), nl, fail.
woman.

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