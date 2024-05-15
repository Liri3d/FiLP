% -*- coding: utf-8 -*-

main :-
    retractall(asked(_,_)),
    fault(Problem),
    !,
    nl,
    write('Проблема в '), write(Problem), write(.), nl.
main :-
    nl,
    write('Не удается определить проблему.'), nl.

problem(disc_format) :-
    query('Компьютер показывает ошибку невозможно отформатировать').

problem(boot_failure) :-
    query('Компьютер показывает ошибку загрузки').

problem(bad_sector) :-
    query('Компьютер показывает ошибку плохого сектора').

problem(cannot_read) :-
    query('Компьютер показывает, что невозможно прочитать данные с указанного устройства').

problem(long_beep) :-
    query('Есть ли длинный звуковой сигнал во время загрузки?').

problem(short_beep) :-
    query('Есть ли короткий звуковой сигнал во время загрузки?').

problem(two_long_beeps) :-
    query('Есть ли два длинных звуковых сигнала во время загрузки?').

problem(two_short_beeps) :-
    query('Есть ли два коротких сигнала во время загрузки?').

problem(blank_display) :-
    query('Во время загрузки отображается пустой экран').

problem(repeating_short_beeps) :-
    query('Во время загрузки повторяются короткие звуковые сигналы?').

problem(continuous_beeps) :-
    query('Есть ли непрерывный звуковой сигнал во время загрузки?').

problem(no_beep) :-
    query('Есть ли звуковой сигнал во время загрузки').

problem(not_printing) :-
    query('Есть ли проблемы с печатью').

problem(missing_dots) :-
    query('Есть ли пропущенный символ во время печати?').

problem(non_uniform_printing) :-
    query('Есть ли единая печать?').

problem(spread_ink) :-
    query('Растекаются ли чернила во время печати?').

problem(paper_jam) :-
    query('Замятие бумаги во время печати').

problem(out_of_paper) :-
    query('Возникла ли ошибка отсутствия бумаги во время печати?').

problem(blue_screen_of_death) :-
    query('Показывает ли компьютер синий экран смерти?').

problem(random_restart) :-
    query('Перезагружается ли компьютер случайно?').

problem(touchpad_not_working) :-
    query('Тачпад не работает?"').

problem(printer_not_responding) :-
    query('Принтер не отвечает?').



fault(touchpad) :-
    problem(touchpad_not_working).

fault(reboot_for_no_reason) :-
    problem(random_restart).

fault(printer) :-
    problem(printer_not_responding),
    problem(not_printing).

fault(computer_death) :-
    problem(blue_screen_of_death).

fault(power_supply) :-
    problem(repeating_short_beeps),
    problem(continuous_beeps),
    problem(blank_display),
    problem(no_beep).

fault(display_adapter) :-
    problem(long_beep),
    problem(two_short_beeps),
    problem(blank_display),
    problem(no_beep).

fault(motherboard) :-
    problem(long_beep),
    problem(short_beep).

fault(hard_disc) :-
    problem(two_short_beeps),
    problem(blank_display).

fault(booting_problem) :-
    problem(bad_sector),
    problem(boot_failure).

fault(floppy_disk_unusable) :-
    problem(bad_sector),
    problem(cannot_read),
    problem(disc_format).

fault(printer_head) :-
    problem(not_printing),
    problem(missing_dots),
    problem(nonuniform_printing).

fault(ribbon) :-
    problem(not_printing),
    problem(missing_dots),
    problem(spread_ink).

fault(paper) :-
    problem(not_printing),
    problem(paper_jam),
    problem(out_of_paper).

query(Prompt) :-
    (   asked(Prompt, Reply) -> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
	assert(asked(Prompt, Reply))
    ),
    Reply = y.