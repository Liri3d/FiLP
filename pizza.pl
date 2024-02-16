% -*- coding: utf-8 -*-

main :-
    retractall(asked(_,_)),
    pizza(Question),
    !,
    nl,
    write('Пицца '), write(Question), write(.), nl.
main :-
    nl,
    write('Не удается определить пиццу.'), nl.

question(small_pizza) :-
    query('Пицца маленькая?').

question(big_pizza) :-
    query('Пицца большая?').

question(pepperoni_pizza) :-
    query('Пицца содержит колбасу?').

question(greenery_pizza) :-
    query('Пицца содержит зелень?').

question(red_sauce_pizza) :-
    query('Пицца содержит красный соус?').

question(white_sauce_pizza) :-
    query('Пицца содержит белый соус?').

pizza(bacon_and_onion) :-
    question(big_pizza),
    question(pepperoni_pizza),
    question(greenery_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(bbq_pork) :-
    question(small_pizza),
    question(pepperoni_pizza),
    question(greenery_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(caprese) :-
    question(small_pizza),
    question(big_pizza),
    question(pepperoni_pizza),
    question(greenery_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(four_cheese) :-
    question(big_pizza),
    question(pepperoni_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(mediterranean) :-
    question(small_pizza),
    question(greenery_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(shrimp_scampi) :-
    question(big_pizza),
    question(greenery_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(sausage_and_mushroom) :-
    question(small_pizza),
    question(pepperoni_pizza),
    question(greenery_pizza),
    question(white_sauce_pizza).

pizza(primavera) :-
    question(big_pizza),
    question(pepperoni_pizza),
    question(greenery_pizza),
    question(white_sauce_pizza).

pizza(buffalo_chicken) :-
    question(big_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(mushroom_and_olive) :-
    question(small_pizza),
    question(greenery_pizza),
    question(white_sauce_pizza).

pizza(spinach_and_feta) :-
    question(small_pizza),
    question(greenery_pizza),
    question(red_sauce_pizza).

pizza(pesto_chicken) :-
    question(small_pizza),
    question(red_sauce_pizza),
    question(white_sauce_pizza).

pizza(bbq_chicken) :-
    question(big_pizza),
    question(red_sauce_pizza).

pizza(philly_cheesesteak) :-
    question(small_pizza),
    question(white_sauce_pizza).

pizza(hawaiian) :-
    question(small_pizza).

pizza(meat_lovers) :-
    question(big_pizza).

pizza(pepperoni) :-
    question(pepperoni_pizza).

pizza(veggie_delight) :-
    question(greenery_pizza).

pizza(margherita) :-
    question(red_sauce_pizza).

pizza(white_pizza) :-
    question(white_sauce_pizza).

query(Prompt) :-
    (   asked(Prompt, Reply) -> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
    assert(asked(Prompt, Reply))
    ),
    Reply = y.

